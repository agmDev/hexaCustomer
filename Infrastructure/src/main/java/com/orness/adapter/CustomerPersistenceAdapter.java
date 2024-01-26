package com.orness.adapter;

import com.orness.hexacustomer.core.exception.CustomerNotFoundException;
import com.orness.hexacustomer.core.model.Customer;
import com.orness.entities.CustomerEntity;
import com.orness.hexacustomer.core.port.persistance.CustomerPersistencePort;
import com.orness.mapper.CustomerEntityMapper;
import com.orness.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerPersistencePort {
    private final CustomerRepository customerRepository;

    private  final CustomerEntityMapper customerEntityMapper;
    @Override
    public void save(Customer customer) {
        customerRepository.saveAndFlush(customerEntityMapper.mapCustomerToCustomerEntity(customer));
    }

    @Override
    public Optional<Customer> findByMail(String mail) {
        CustomerEntity customerEntity = customerRepository.findByMail(mail).orElseThrow(CustomerNotFoundException::new);
        return Optional.of(customerEntityMapper.mapCustomerEntityToCustomer(customerEntity));
    }

}
