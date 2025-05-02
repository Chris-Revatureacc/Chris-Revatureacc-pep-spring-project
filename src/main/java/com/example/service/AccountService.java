package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccount(account.getUsername());
        if(account.getUsername() == "") {
            throw new IllegalArgumentException("Username cannot be blank"); 
        } else if(account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be atlease 4 characters in length");
        } else if(accountOptional.isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }
        return accountRepository.save(account);
    }

    public Account loginAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccount(account.getUsername());
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        }
        else return null;
    }
}
