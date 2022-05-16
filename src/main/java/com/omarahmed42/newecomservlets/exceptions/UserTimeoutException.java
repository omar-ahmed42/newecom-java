package com.omarahmed42.newecomservlets.exceptions;

public class UserTimeoutException extends RuntimeException {
    public UserTimeoutException(String message){
        super(message);
    }
}
