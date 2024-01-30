package com.orness.adapter;

import com.orness.entities.AgifyEntity;
import com.orness.hexacustomer.core.port.persistance.AgifyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AgifyAdapter implements AgifyPort {

    private final RestTemplate agifyClient;


    @Override
    public int age(String firstName) {
        int age = 0;
        try{
            AgifyEntity response = agifyClient.getForObject("/?name=" + firstName, AgifyEntity.class);
            if (response != null && response.getAge() > 0 && response.getAge() <= 150){
                age = response.getAge();
            }
        }
        catch(RestClientResponseException e) {
            return age;
        }
        return age;
    }
}
