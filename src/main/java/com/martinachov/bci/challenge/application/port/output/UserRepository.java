package com.martinachov.bci.challenge.application.port.output;

import com.martinachov.bci.challenge.application.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);

    void delete(UUID id);
}
