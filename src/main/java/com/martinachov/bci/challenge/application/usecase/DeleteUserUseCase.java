package com.martinachov.bci.challenge.application.usecase;

import com.martinachov.bci.challenge.application.exception.UserNotFoundException;
import com.martinachov.bci.challenge.application.port.input.DeleteUserInputPort;
import com.martinachov.bci.challenge.application.port.output.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUserUseCase implements DeleteUserInputPort {

    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(UUID id) throws UserNotFoundException {
        if(!userRepository.findById(id).isPresent())
            throw new UserNotFoundException("User not found!");

        userRepository.delete(id);
    }
}
