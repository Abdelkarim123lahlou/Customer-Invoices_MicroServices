package com.invoiceservice.training.exceptions;

public class CustomerNotFoundExceptions extends RuntimeException {
    public CustomerNotFoundExceptions(String message) {
        super(message);
    }
}
