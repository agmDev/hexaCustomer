package com.orness.request;


import lombok.Getter;


@Getter
public class CreateCustomerRequest {
    private String mail;
    private String firstname;
    private String lastname;
    private Integer age;
}
