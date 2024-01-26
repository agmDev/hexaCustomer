package com.orness.hexacustomer.core.port.primary;

import com.orness.hexacustomer.core.model.Customer;

import java.util.UUID;

public interface CustomerPort {
    public UUID createCustomer(Customer customer);
    public Customer getCustomerByMail(String mail);

    public boolean existByMail(String mail);
}
