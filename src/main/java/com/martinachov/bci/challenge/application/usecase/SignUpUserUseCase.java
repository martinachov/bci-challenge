package com.martinachov.bci.challenge.application.usecase;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.domain.UserRegistrationData;
import com.martinachov.bci.challenge.application.exception.UserAlreadyExistException;
import com.martinachov.bci.challenge.application.port.input.SignUpUserInputPort;
import com.martinachov.bci.challenge.application.port.output.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignUpUserUseCase implements SignUpUserInputPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(UserRegistrationData userData) throws UserAlreadyExistException {

        // Se chequea existencia de email en BD
        if(userRepository.findByEmail(userData.getEmail()).isPresent())
            throw  new UserAlreadyExistException("User already exist !!");

        // Se guarda el usuario
        User newUser = User.builder()
                .email(userData.getEmail())
                .name(userData.getName())
                .password(passwordEncoder.encode(userData.getPassword()))
                .phones(userData.getPhones())
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(Boolean.TRUE)
                .build();

        User savedUser = userRepository.save(newUser);

        return savedUser;
    }
}
