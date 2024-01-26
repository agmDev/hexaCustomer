package com.orness.hexacustomer.core.port.persistance;

import com.orness.hexacustomer.core.model.Customer;

import java.util.Optional;

public interface CustomerPersistencePort {

    void save(Customer customer);
    Optional<Customer> findByMail(String mail);

}
