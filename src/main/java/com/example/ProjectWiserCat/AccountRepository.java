package com.example.ProjectWiserCat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
     Account findByUsername(String username);
     Account findByPassword(String password);
     Account findByToken(UUID token);
}
