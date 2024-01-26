package com.orness.hexacustomer.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Customer {
    private UUID id;
    private String mail;
    private String firstname;
    private String lastname;
    private Integer age;

    public Customer(UUID id, String firstname, String lastname, String mail){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
    }
}