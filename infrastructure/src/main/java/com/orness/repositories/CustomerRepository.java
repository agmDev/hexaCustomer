package com.orness.repositories;

import com.orness.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByMail(String mail);

}