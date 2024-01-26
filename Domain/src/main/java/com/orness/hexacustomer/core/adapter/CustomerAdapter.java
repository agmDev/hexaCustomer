package com.orness.hexacustomer.core.adapter;

import com.orness.hexacustomer.core.exception.CustomerNotFoundException;
import com.orness.hexacustomer.core.model.Customer;
import com.orness.hexacustomer.core.port.persistance.AgifyPort;
import com.orness.hexacustomer.core.port.persistance.CustomerPersistencePort;
import com.orness.hexacustomer.core.port.primary.CustomerPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CustomerAdapter implements CustomerPort {

    private final CustomerPersistencePort customerPersistencePort;
    private final AgifyPort agifyPort;

    @Override
    public UUID createCustomer(Customer customer) {
        customer.setId(UUID.randomUUID());

        if (customer.getAge() == null || customer.getAge() == 0) {
            customer.setAge(agifyPort.age(customer.getFirstname()));
        }
        this.customerPersistencePort.save(customer);
        return customer.getId();
    }

    @Override
    public Customer getCustomerByMail(String mail){
        return customerPersistencePort
                .findByMail(mail).orElseThrow(CustomerNotFoundException::new);
    }


}
