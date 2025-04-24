package com.preparazione.preparazione.repo;
import com.preparazione.preparazione.entities.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, Long> {
}
