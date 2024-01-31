package com.orness.controller;

import com.orness.hexacustomer.core.model.Customer;
import com.orness.hexacustomer.core.port.primary.CustomerPort;
import com.orness.mapper.CustomerMapper;
import com.orness.request.CreateCustomerRequest;
import com.orness.response.CreateCustomerResponse;
import com.orness.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerMapper customerMapper;
    private final CustomerPort customerPort;

    @Autowired
    public CustomerController(CustomerPort customerPort, CustomerMapper customerMapper) {
        this.customerPort = customerPort;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/{mail}")
    public CustomerResponse getCustomer(@PathVariable("mail") String mail) {
        Customer customer = customerPort.getCustomerByMail(mail);
        return customerMapper.mapCustomerToCustomerResponse(customer);
    }

    @PostMapping
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest request) {
        return new CreateCustomerResponse(customerPort.createCustomer(customerMapper.mapCreateRequestToCustomer(request)));
    }
}