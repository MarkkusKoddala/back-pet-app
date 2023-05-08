package com.example.ProjectWiserCat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    public AccountRepository accountRepository;

    public boolean isAccount (Account user){
        if (accountRepository.findByUsername(user.getUsername()) != null){
            return accountRepository.findByPassword(user.getPassword()) != null;
        }
        return false;
    }

    public String findUserByToken(UUID token) {
        Account user = accountRepository.findByToken(token);
        return user.getUserId();
        }

    public void updateToken(String username, UUID newToken) {
        Account account = accountRepository.findByUsername(username);
        account.setToken(newToken);
        accountRepository.save(account);
    }
}

