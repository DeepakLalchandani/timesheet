package com.techsopi.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techsopi.constant.AppConstant;
import com.techsopi.entity.ClientEntity;
import com.techsopi.exception.ClientAlreadyExistsException;
import com.techsopi.exception.ClientNotFoundException;
import com.techsopi.model.ClientDTO;
import com.techsopi.repository.ClientRepository;
import com.techsopi.service.ClientService;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Optional<ClientEntity> getClientById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientDTO updateClient(ClientDTO updatedClient, Long clientId) {

        Optional<ClientEntity> existingClientOptional = clientRepository.findByMobile(updatedClient.getMobile());

        ClientEntity clientById = clientRepository.findById(clientId).orElseThrow(() ->
                new ClientNotFoundException("client with Id not found"));

        if (existingClientOptional.isPresent() && !existingClientOptional.get().getClientId().equals(clientId)) {

            throw new ClientAlreadyExistsException("client with mobile number already exist");
        }

        ClientEntity clientEntity = mapDtoToEntity(updatedClient);
        ClientEntity updatedEntity = clientRepository.save(clientEntity);
        return mapEntityToDto(updatedEntity);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Optional<ClientEntity> findByMobile = clientRepository.findByMobile(clientDTO.getMobile());

        if (findByMobile.isPresent()) {
            throw new ClientAlreadyExistsException("Client with this mobile number already exists");
        }
        ClientEntity clientEntity = mapDtoToEntity(clientDTO);
        ClientEntity savedEntity = clientRepository.save(clientEntity);
        return mapEntityToDto(savedEntity);
    }

    private ClientEntity mapDtoToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }

    private ClientDTO mapEntityToDto(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientDTO.class);
    }

    @Override
    public String deleteClient(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return AppConstant.CLIENT_DELETED_SUCCESSFULLY;
        } else {
            throw new ClientNotFoundException("Client with ID " + clientId + " not found");
        }
    }

    @Override
    public ClientDTO getClientById(Long clientId) {

        Optional<ClientEntity> client = clientRepository.findById(clientId);
        if (client.isEmpty()) {
            throw new ClientNotFoundException("no client with id" + clientId + " " + "doesnot exist");
        } else {
            return mapEntityToDto(client.get());
        }
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<ClientEntity> clientEntityList = clientRepository.findAll();
        if (clientEntityList.isEmpty()) {
            throw new ClientNotFoundException("no client exist");
        }
        return clientEntityList.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

}





