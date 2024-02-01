package com.techsopi.repository;

import com.techsopi.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public Optional<ClientEntity> findByMobile(String mobile);

}
 
