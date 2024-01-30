package com.orness.configuration;

import com.orness.hexacustomer.core.adapter.CustomerAdapter;
import com.orness.hexacustomer.core.port.persistance.AgifyPort;
import com.orness.hexacustomer.core.port.persistance.CustomerPersistencePort;
import com.orness.hexacustomer.core.port.primary.CustomerPort;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    CustomerPort customerPort(CustomerPersistencePort customerPersistencePort, AgifyPort agifyPort, Validator validator){
        return new CustomerAdapter(customerPersistencePort, agifyPort, validator);
    }
}
