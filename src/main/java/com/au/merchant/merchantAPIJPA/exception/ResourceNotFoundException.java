package com.au.merchant.merchantAPIJPA.exception;

public class ResourceNotFoundException extends Exception {
    public static final long serialVersionUID = 1L;

    public ResourceNotFoundException(){
        System.out.println("Resource not found Exception");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
