package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) {
        // we have to Check if username is not blank and password is at least 4 characters
        if (account.getUsername() == null || account.getUsername().trim().isEmpty() || 
            account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }

        // we have to Check if username already exists
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            return null;
        }

        return accountRepository.save(account);
    }

    public Account login(Account account) {
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount != null && existingAccount.getPassword().equals(account.getPassword())) {
            return existingAccount;
        }
        return null;
    }
}
