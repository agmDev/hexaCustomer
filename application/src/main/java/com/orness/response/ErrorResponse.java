package com.orness.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String description;
    private UUID stacktraceId;
    private String title;
}
