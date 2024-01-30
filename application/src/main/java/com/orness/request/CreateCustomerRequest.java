package com.orness.request;

import lombok.Getter;


@Getter
public class CreateCustomerRequest {
    private String firstname;
    private String lastname;
    private String mail;
    private Integer age;
}
