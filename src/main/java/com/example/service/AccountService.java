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
            return null;
        } else if(account.getPassword().length() < 4) {
            return null;
        } else if(accountOptional.isPresent()) {
            return null;
        }
        return accountRepository.save(account);
    }

    public Account loginAccount(String username) {
        Optional<Account> accountOptional = accountRepository.findAccount(username);
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        }
        else return null;
    }
}
