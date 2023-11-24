package com.martinachov.bci.challenge.application.usecase;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;
import com.martinachov.bci.challenge.application.port.input.RetrieveUserInputPort;
import com.martinachov.bci.challenge.application.port.output.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RetrieveUserUseCase implements RetrieveUserInputPort {

    private final UserRepository userRepository;

    public RetrieveUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new UserNotFoundException("User with email: " + email + " not found!!");

        return user.get();
    }

    @Override
    public User getUserById(UUID id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User with id: " + id + " not found!!");
        return user.get();
    }
}
