package com.martinachov.bci.challenge.application.port.input;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.domain.UserRegistrationData;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;

import java.util.UUID;

public interface UpdateUserInputPort {
    void updateToken(String email, String token) throws UserNotFoundException;

    User updateUserData(UUID id, UserRegistrationData newUserData) throws UserNotFoundException;

}
