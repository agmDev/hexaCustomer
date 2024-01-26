package com.orness.mapper;

import com.orness.entities.CustomerEntity;
import com.orness.hexacustomer.core.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerEntityMapper {
    Customer mapCustomerEntityToCustomer(CustomerEntity customer);
    CustomerEntity mapCustomerToCustomerEntity(Customer customer);
}
