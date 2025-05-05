package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(accountOptional.isPresent()) {
            return null;
        }
        return accountRepository.save(account);
    }

    public Account loginAccount(String username, String password) {
        Optional<Account> accountOptional = accountRepository.login(username, password);
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        }
        else return null;
    }
}
