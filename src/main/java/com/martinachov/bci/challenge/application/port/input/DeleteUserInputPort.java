package com.martinachov.bci.challenge.application.port.input;

import com.martinachov.bci.challenge.application.exception.UserNotFoundException;

import java.util.UUID;

public interface DeleteUserInputPort {

    public void deleteUser(UUID id) throws UserNotFoundException;
}
