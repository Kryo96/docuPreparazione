package com.preparazione.preparazione.repo;
import com.preparazione.preparazione.entities.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, Long> {
    Optional<WebsiteUser> findByUsername(String username);
}
