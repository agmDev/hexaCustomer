package com.orness.mapper;

import com.orness.hexacustomer.core.model.Customer;
import com.orness.request.CreateCustomerRequest;
import com.orness.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer mapCreateRequestToCustomer(CreateCustomerRequest request);
    CustomerResponse mapCustomerToCustomerResponse(Customer customer);
}
