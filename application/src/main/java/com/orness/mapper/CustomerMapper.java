package com.orness.mapper;

import com.orness.hexacustomer.core.model.Customer;
import com.orness.request.CreateCustomerRequest;
import com.orness.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer mapCreateRequestToCustomer(CreateCustomerRequest request);
    CustomerResponse mapCustomerToCustomerResponse(Customer customer);
}
