package com.orness.hexacustomer.core.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Customer {
    @NotBlank(message = "firstname should not be left blank")
    @Size(min = 1, max = 200, message = "firstname should not be longer than 200 characters")
    @Pattern(regexp = "^[a-zA-Z\\-À-ÿ' ]*$", message = "There should be no special characters in the firstname")
    private String firstname;
    @NotBlank(message = "lastname should not be left blank")
    @Size(min = 1, max = 200, message = "lastname should not be longer than 200 characters")
    @Pattern(regexp = "^[a-zA-Z\\-À-ÿ' ]*$", message = "There should be no special characters in the lastname")
    private String lastname;
    @NotBlank
    @Email(message = "Email should be valid")
    private String mail;
    @Max(value = 150, message = "age should not be more than 150")
    @Min(value = 0, message = "age should not be less than 0")
    private Integer age;

    private UUID id;

    public Customer(UUID id, String firstname, String lastname, String mail){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
    }
}