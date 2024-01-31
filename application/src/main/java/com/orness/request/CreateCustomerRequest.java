package com.orness.request;


import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCustomerRequest {
    private UUID id;
    private String mail;
    private String firstname;
    private String lastname;
    private Integer age;
}
