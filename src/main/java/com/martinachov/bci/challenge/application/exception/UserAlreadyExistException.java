package com.martinachov.bci.challenge.application.exception;

public class UserAlreadyExistException extends BaseException{
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
