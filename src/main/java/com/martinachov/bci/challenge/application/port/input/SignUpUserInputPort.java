package com.martinachov.bci.challenge.application.port.input;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.domain.UserRegistrationData;
import com.martinachov.bci.challenge.application.exception.UserAlreadyExistException;

public interface SignUpUserInputPort {
    User signUp(UserRegistrationData userData) throws UserAlreadyExistException;
}
