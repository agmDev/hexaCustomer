package com.orness.hexacustomer.core.port.primary;

import com.orness.hexacustomer.core.model.Customer;

import java.util.UUID;

public interface CustomerPort {
    UUID createCustomer(Customer customer);

    Customer getCustomerByMail(String mail);

    boolean existByMail(String mail);
}
