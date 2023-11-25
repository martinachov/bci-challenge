package com.martinachov.bci.challenge.adapter.in.web;

import com.martinachov.bci.challenge.adapter.in.web.dto.*;
import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.domain.UserRegistrationData;
import com.martinachov.bci.challenge.application.exception.BaseException;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;
import com.martinachov.bci.challenge.application.port.input.DeleteUserInputPort;
import com.martinachov.bci.challenge.application.port.input.RetrieveUserInputPort;
import com.martinachov.bci.challenge.application.port.input.SignUpUserInputPort;
import com.martinachov.bci.challenge.application.port.input.UpdateUserInputPort;
import com.martinachov.bci.challenge.infrastructure.security.AuthServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final SignUpUserInputPort signUpUserPort;
    private final UpdateUserInputPort updateUserPort;
    private final RetrieveUserInputPort retrieveUserPort;
    private final DeleteUserInputPort deleteUserPort;
    private final ModelMapper mapper;
    private final AuthServiceImpl authService;

    public UserRestController(SignUpUserInputPort signUpUserPort, UpdateUserInputPort updateUserPort, RetrieveUserInputPort retrieveUserPort, DeleteUserInputPort deleteUserPort, ModelMapper mapper, AuthServiceImpl authService) {
        this.signUpUserPort = signUpUserPort;
        this.updateUserPort = updateUserPort;
        this.retrieveUserPort = retrieveUserPort;
        this.deleteUserPort = deleteUserPort;
        this.mapper = mapper;
        this.authService = authService;
    }

    @PostMapping(value="/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResponseDTO> registerUser(@RequestBody @Valid RegistrationRequestDTO request) throws BaseException {

        UserRegistrationData userRegistrationData = mapper.map(request, UserRegistrationData.class);
        User userRegistered = signUpUserPort.signUp(userRegistrationData);

        String token = authService.generateToken(request.getEmail(), request.getPassword());
        updateUserPort.updateToken(userRegistered.getEmail(), token);

        return ResponseEntity.status(HttpStatus.CREATED).body(RegistrationResponseDTO.builder()
                        .id(userRegistered.getId())
                        .token(token)
                        .created(userRegistered.getCreated())
                        .lastLogin(userRegistered.getLastLogin())
                        .isActive(userRegistered.getIsActive())
                        .email(userRegistered.getEmail())
                .build());
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") UUID id) throws UserNotFoundException {
        User user = retrieveUserPort.getUserById(id);

        return ResponseEntity.ok(UserResponseDTO.builder()
                        .user(mapper.map(user, UserDTO.class))
                        .token(user.getToken())
                        .created(user.getCreated())
                        .isActive(user.getIsActive())
                        .lastLogin(user.getLastLogin())
                .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") UUID id, @RequestBody UpdateUserRequestDTO userData) throws UserNotFoundException {
        UserRegistrationData newUserData = mapper.map(userData, UserRegistrationData.class);
        User updatedUser = updateUserPort.updateUserData(id, newUserData);

        return ResponseEntity.ok(UserResponseDTO.builder()
                .user(mapper.map(updatedUser, UserDTO.class))
                .token(updatedUser.getToken())
                .created(updatedUser.getCreated())
                .isActive(updatedUser.getIsActive())
                .lastLogin(updatedUser.getLastLogin())
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteUserResponseDTO> deleteUser(@PathVariable("id") UUID id) throws UserNotFoundException {
        deleteUserPort.deleteUser(id);
        return ResponseEntity.ok(DeleteUserResponseDTO.builder().message("User successfully deleted!").build());
    }


}
