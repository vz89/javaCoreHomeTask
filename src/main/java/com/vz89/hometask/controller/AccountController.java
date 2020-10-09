package com.vz89.hometask.controller;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.json.JsonAccountRepositoryImpl;

import java.util.List;

public class AccountController {
    private AccountRepository accountRepository = new JsonAccountRepositoryImpl();

    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account create(String name) {
        return accountRepository.save(new Account(name));
    }

    public Account update(Long id, AccountStatus accountStatus) {
        return accountRepository.update(new Account(id, accountStatus));
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
