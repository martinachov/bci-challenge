package com.martinachov.bci.challenge.infrastructure.persistence.reposirory;

import com.martinachov.bci.challenge.infrastructure.persistence.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAPhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
