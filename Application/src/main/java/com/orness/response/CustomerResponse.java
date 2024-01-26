package com.orness.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CustomerResponse {
    private final UUID id;
    private final String firstname;
    private final String lastname;
    private int age;
    private final String mail;
}
