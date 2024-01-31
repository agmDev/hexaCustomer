package com.orness.hexacustomer.core.adapter;

import com.orness.hexacustomer.core.exception.CustomerNotFoundException;
import com.orness.hexacustomer.core.exception.UniqueEmailException;
import com.orness.hexacustomer.core.model.Customer;
import com.orness.hexacustomer.core.port.persistance.AgifyPort;
import com.orness.hexacustomer.core.port.persistance.CustomerPersistencePort;
import com.orness.hexacustomer.core.port.primary.CustomerPort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomerAdapter implements CustomerPort {

    private final CustomerPersistencePort customerPersistencePort;
    private final AgifyPort agifyPort;
    private final Validator validator;

    @Override
    public UUID createCustomer(Customer customer) {
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        if (!violations.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Customer> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }


        if (existByMail(customer.getMail())){
            throw (new UniqueEmailException());
        }
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

    @Override
    public boolean existByMail(String mail){
        return customerPersistencePort.findByMail(mail).isPresent();
    }


}
