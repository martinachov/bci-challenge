package com.martinachov.bci.challenge.application.port.input;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;

import java.util.UUID;

public interface RetrieveUserInputPort {
    User getUserByEmail(String email) throws UserNotFoundException;
    User getUserById(UUID id) throws UserNotFoundException;
}
