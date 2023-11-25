package com.martinachov.bci.challenge.adapter.in.web;


import com.martinachov.bci.challenge.adapter.in.web.dto.LoginCredentialsDTO;
import com.martinachov.bci.challenge.adapter.in.web.dto.TokenDTO;
import com.martinachov.bci.challenge.application.exception.UserNotFoundException;
import com.martinachov.bci.challenge.application.port.input.UpdateUserInputPort;
import com.martinachov.bci.challenge.infrastructure.security.AuthServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthServiceImpl authService;
    private final UpdateUserInputPort updateUserPort;

    public AuthRestController(AuthServiceImpl authService, UpdateUserInputPort updateUserPort) {
        this.authService = authService;
        this.updateUserPort = updateUserPort;
    }

    @PostMapping(value="/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> loginUser(@RequestBody LoginCredentialsDTO body) throws UserNotFoundException {
        String token = authService.generateToken(body.getEmail(), body.getPassword());
        updateUserPort.updateToken(body.getEmail(), token);
        return ResponseEntity.ok(TokenDTO.builder().token(token).build());
    }

}
