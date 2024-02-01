package com.techsopi.service;

import com.techsopi.entity.ClientEntity;
import com.techsopi.model.ClientDTO;
import java.util.List;

import java.util.Optional;


public interface ClientService {

    /**
     * @param id
     * @return
     */
    Optional<ClientEntity> getClientById(long id);

    /**
     * @param clientDTO
     * @param clientId
     * @return
     */
    ClientDTO updateClient(ClientDTO clientDTO,Long clientId);


    /**
     * @param clientDTO
     * @return ClientDTO
     */
    public ClientDTO createClient(ClientDTO clientDTO);


    /**
     * @param clientId
     * @return
     */
    public String deleteClient(Long clientId);

    /**
     * @param clientId
     * @return clientDTO
     */
    public ClientDTO getClientById(Long clientId);

    /**
     * @param
     * @return List
     */
    public List<ClientDTO> getAllClients();

}
