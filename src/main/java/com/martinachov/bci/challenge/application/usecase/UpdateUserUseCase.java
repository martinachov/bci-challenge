package com.martinachov.bci.challenge.application.usecase;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.domain.UserRegistrationData;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;
import com.martinachov.bci.challenge.application.port.input.UpdateUserInputPort;
import com.martinachov.bci.challenge.application.port.output.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUserUseCase implements UpdateUserInputPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void updateToken(String email, String token) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }

        User updatedUser = user.get();
        updatedUser.setToken(token);
        userRepository.save(updatedUser);

    }

    @Override
    public User updateUserData(UUID id, UserRegistrationData newUserData) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }

        User updatedUser = user.get();
        updatedUser.setEmail(newUserData.getEmail());
        updatedUser.setName(newUserData.getName());
        updatedUser.setPhones(newUserData.getPhones());
        updatedUser.setPassword(passwordEncoder.encode(newUserData.getPassword()));

        userRepository.save(updatedUser);
        return updatedUser;
    }

}
