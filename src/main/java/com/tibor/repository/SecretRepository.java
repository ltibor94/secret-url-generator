package com.tibor.repository;

import com.tibor.model.SecretData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface SecretRepository extends JpaRepository<SecretData, String> {

    Optional<SecretData> findByHashAndAvailable(String hash, boolean available);
}
