package com.techsopi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsopi.controller.ClientApiDelegateImpl;
import com.techsopi.entity.ClientEntity;
import com.techsopi.model.ClientDTO;
import com.techsopi.service.ClientService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
class TimesheetApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientApiDelegateImpl clientApiDelegate;

    @Test
    public void givenClientObject_whenCreateClient_thenReturnSavedClient() throws Exception {

        ClientEntity client = ClientEntity.builder()
                .clientName("tcs")
                .currency("rupee")
                .billingMethod("billable")
                .emailId("deepakl.2000@gmail.com")
                .firstName("Deepak")
                .lastName("Lalchandani")
                .phone("9886764980")
                .mobile("9886764980")
                .streetAddress("new york city")
                .city("Bangalore")
                .state("Karnataka")
                .zip("560078")
                .country("india")
                .industry("marketing")
                .companySize(500)
                .description("demo company description")
                .build();
        given(clientService.createClient(any(ClientDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientName", Matchers.equalTo(client.getClientName())))
                .andExpect(jsonPath("$.currency", Matchers.equalTo(client.getCurrency())))
                .andExpect(jsonPath("$.billingMethod", Matchers.equalTo(client.getBillingMethod())))
                .andExpect(jsonPath("$.emailId", Matchers.equalTo(client.getEmailId())))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo(client.getFirstName())))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo(client.getLastName())))
                .andExpect(jsonPath("$.phone", Matchers.equalTo(client.getPhone())))
                .andExpect(jsonPath("$.mobile", Matchers.equalTo(client.getMobile())))
                .andExpect(jsonPath("$.streetAddress", Matchers.equalTo(client.getStreetAddress())))
                .andExpect(jsonPath("$.city", Matchers.equalTo(client.getCity())))
                .andExpect(jsonPath("$.state", Matchers.equalTo(client.getState())))
                .andExpect(jsonPath("$.zip", Matchers.equalTo(client.getZip())))
                .andExpect(jsonPath("$.country", Matchers.equalTo(client.getCountry())))
                .andExpect(jsonPath("$.industry", Matchers.equalTo(client.getIndustry())))
                .andExpect(jsonPath("$.companySize", Matchers.equalTo(client.getCompanySize())))
                .andExpect(jsonPath("$.description", Matchers.equalTo(client.getDescription())));

    }

    @Test
    public void testDeleteClient() throws Exception {
        long clientId = 1L;

        given(clientService.deleteClient(clientId)).willReturn("Client Deleted Successfully.");

        mockMvc.perform(delete("/api/v1/clients/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(clientService).deleteClient(clientId);
    }

    @Test
    public void testGetClientById() {
        Long clientId = 1L;
        ClientDTO mockClient = new ClientDTO();
        mockClient.setClientId(1L);
        mockClient.setClientName("wipro");
        mockClient.setCurrency("dollar");
        mockClient.setBillingMethod("billable");
        mockClient.setEmailId("abc@example.com");
        mockClient.setFirstName("abc");
        mockClient.setLastName("singh");
        mockClient.setPhone("987723567");
        mockClient.setMobile("12345678");
        mockClient.setStreetAddress("asdfgh");
        mockClient.setCity("delhi");
        mockClient.setState("bihar");
        mockClient.setZip("32920");
        mockClient.setCountry("India");
        mockClient.setIndustry("xyz");
        mockClient.setCompanySize(200);
        mockClient.setDescription("IT gobal firm");
        when(clientService.getClientById(clientId)).thenReturn(mockClient);
        System.out.println(mockClient);
        ResponseEntity<ClientDTO> responseEntity = clientApiDelegate.getClientById(clientId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(clientService, times(1)).getClientById(clientId);
        verifyNoMoreInteractions(clientService);
    }
    @Test
    void testGetClients() {
        List<ClientDTO> mockClients = Arrays.asList(
                new ClientDTO().clientId(1l)
                        .clientName("TCS")
                        .currency("billable")
                        .billingMethod("dollar")
                        .emailId("rajat@example.com")
                        .firstName("rahat")
                        .lastName("kumar")
                        .phone("9102754326")
                        .mobile("8765432134")
                        .streetAddress("abc")
                        .city("Ujjain")
                        .state("Rajasthan")
                        .zip("302016")
                        .country("India")
                        .industry("IT")
                        .companySize(50)
                        .description("IT company"));
        when(clientService.getAllClients()).thenReturn(mockClients);
        System.out.println(mockClients);
        ResponseEntity<List<ClientDTO>> responseEntity = clientApiDelegate.getClients();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        verify(clientService, times(1)).getAllClients();
        verifyNoMoreInteractions(clientService);
    }

    @Test
    public void givenUpdatedClient_whenUpdateClient_thenReturnUpdateClientObject() throws Exception {

        long clientId = 1L;
        ClientEntity savedClient = ClientEntity.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .emailId("ramesh@gmail.com")
                .currency("rupee")
                .clientName("abc")
                .billingMethod("billable")
                .city("Bengaluru")
                .state("Karnataka")
                .companySize(1667)
                .streetAddress("abc abc")
                .phone("12345")
                .mobile("748726899")
                .industry("Techsopi")
                .country("India")
                .zip("2878743")
                .description("check it for testing")
                .build();

        ClientEntity updatedClient = ClientEntity.builder()
                .firstName("Ram")
                .lastName("Jadhav")
                .emailId("ram@gmail.com")
                .currency("dollar")
                .clientName("TCS")
                .billingMethod("billable")
                .city("miami")
                .state("california")
                .companySize(4764)
                .streetAddress("def xyz")
                .phone("1234567")
                .mobile("1234567890")
                .industry("TCS")
                .country("USA")
                .zip("6585858")
                .description("testing completed")
                .build();
        given(clientService.getClientById(clientId)).willReturn(Optional.of(savedClient));
        given(clientService.updateClient(any(ClientDTO.class),any(Long.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/v1/clients/{id}", clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedClient)));


        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientName", Matchers.equalTo(updatedClient.getClientName())))
                .andExpect(jsonPath("$.currency", Matchers.equalTo(updatedClient.getCurrency())))
                .andExpect(jsonPath("$.billingMethod", Matchers.equalTo(updatedClient.getBillingMethod())))
                .andExpect(jsonPath("$.emailId", Matchers.equalTo(updatedClient.getEmailId())))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo(updatedClient.getFirstName())))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo(updatedClient.getLastName())))
                .andExpect(jsonPath("$.phone", Matchers.equalTo(updatedClient.getPhone())))
                .andExpect(jsonPath("$.mobile", Matchers.equalTo(updatedClient.getMobile())))
                .andExpect(jsonPath("$.streetAddress", Matchers.equalTo(updatedClient.getStreetAddress())))
                .andExpect(jsonPath("$.city", Matchers.equalTo(updatedClient.getCity())))
                .andExpect(jsonPath("$.state", Matchers.equalTo(updatedClient.getState())))
                .andExpect(jsonPath("$.zip", Matchers.equalTo(updatedClient.getZip())))
                .andExpect(jsonPath("$.country", Matchers.equalTo(updatedClient.getCountry())))
                .andExpect(jsonPath("$.industry", Matchers.equalTo(updatedClient.getIndustry())))
                .andExpect(jsonPath("$.companySize", Matchers.equalTo(updatedClient.getCompanySize())))
                .andExpect(jsonPath("$.description", Matchers.equalTo(updatedClient.getDescription())));

    }
}

