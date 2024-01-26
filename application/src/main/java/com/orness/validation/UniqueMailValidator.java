package com.orness.validation;

import com.orness.hexacustomer.core.port.primary.CustomerPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueMailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final CustomerPort customerPort;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value == null || value.isBlank() || !customerPort.existByMail(value);
    }
}
