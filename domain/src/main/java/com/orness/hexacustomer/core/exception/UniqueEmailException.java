package com.orness.hexacustomer.core.exception;

public class UniqueEmailException extends RuntimeException{
    public UniqueEmailException() {
        super("Email already taken");
    }
}