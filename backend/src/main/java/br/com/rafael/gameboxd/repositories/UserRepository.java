package br.com.rafael.gameboxd.repositories;

import br.com.rafael.gameboxd.domain.credential.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
