package com.leasing.app.repository;

import com.leasing.app.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebUserRepository extends JpaRepository<WebUser,Long> {
    Optional<WebUser> findByUsername(String username);
}
