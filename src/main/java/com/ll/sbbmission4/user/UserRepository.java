package com.ll.sbbmission4.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Site_User, Long> {
    Optional<Site_User> findByusername(String username);
}
