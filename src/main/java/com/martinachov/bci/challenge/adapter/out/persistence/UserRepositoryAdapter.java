package com.martinachov.bci.challenge.adapter.out.persistence;

import com.martinachov.bci.challenge.application.domain.User;
import com.martinachov.bci.challenge.application.port.output.UserRepository;
import com.martinachov.bci.challenge.infrastructure.persistence.entity.UserEntity;
import com.martinachov.bci.challenge.infrastructure.persistence.reposirory.JPAUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final JPAUserRepository repository;
    private final ModelMapper modelMapper;

    public UserRepositoryAdapter(JPAUserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        repository.save(userEntity);
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(entity -> modelMapper.map(entity, User.class));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> modelMapper.map(entity, User.class));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
