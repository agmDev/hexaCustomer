package com.orness.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CreateCustomerResponse {
    private final UUID id;
}
