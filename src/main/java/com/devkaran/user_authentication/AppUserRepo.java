package com.devkaran.user_authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
