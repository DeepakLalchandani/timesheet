package com.techsopi.controller;

import com.techsopi.model.ClientDTO;
import com.techsopi.service.ClientService;
import jakarta.validation.Valid;
import com.techsopi.controller.validations.Validation;
import com.techsopi.exception.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientApiDelegateImpl implements ClientsApiDelegate {

    @Autowired
    private ClientService clientService;

    /**
     * <p> Adding the Client
     * </p>
     *
     * @param clientDTO (optional)
     * @return Information about saved client
     */
    @Override
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        List<String> errors = Validation.validateClientDTO(clientDTO);
        if (errors.isEmpty()) {
            ClientDTO savedClient = clientService.createClient(clientDTO);
            return new ResponseEntity<ClientDTO>(savedClient, HttpStatus.CREATED);
        }
        throw new InvalidFieldException(errors.get(0));
    }

    /**
     * DELETE /clients/{clientId} : Delete a specific client by ID
     *
     * @param clientId (required)
     * @return Client deleted successfully (status code 204)
     * @see ClientsApi#deleteClientById
     */
    public ResponseEntity<Void> deleteClientById(@PathVariable("clientId") Long clientId) {

        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * GET /clients/{clientId} : Get a specific client by ID
     *
     * @param id (required)
     * @return successful response (status code 200)
     * * or conflicting information (status code 409)
     */
    @Override
    public ResponseEntity<ClientDTO> getClientById(@PathVariable @Valid Long id) {
        ClientDTO clientById = clientService.getClientById(id);
        return new ResponseEntity<ClientDTO>(clientById, HttpStatus.OK);
    }

    /**
     * GET  /clients: Getting the client List
     *
     * @param
     * @return successful response (status code 200)
     * or conflicting information (status code 409)
     */
    @Override
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> allClients = clientService.getAllClients();
        return new ResponseEntity<List<ClientDTO>>(allClients, HttpStatus.OK);
    }

    /**
     * PUT /clients/{clientId} : Update a specific client by ID
     *
     * @param clientId  (required)
     * @param clientDTO (optional)
     * @return Client updated successfully (status code 200)
     * @see ClientsApi#updateClientById
     */
    @Override
    public ResponseEntity<ClientDTO> updateClientById(@PathVariable("clientId") Long clientId,
                                                      @RequestBody ClientDTO clientDTO) {
        clientDTO.setClientId(clientId);
        List<String> errors = Validation.validateClientDTO(clientDTO);
        if (errors.isEmpty()) {
            ClientDTO updatedClient = clientService.updateClient(clientDTO, clientId);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        }
        throw new InvalidFieldException(errors.get(0));
    }
}
