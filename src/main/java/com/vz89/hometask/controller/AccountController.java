package com.vz89.hometask.controller;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.service.AccountService;
import com.vz89.hometask.service.AccountServiceImpl;

import java.util.List;

public class AccountController {
    private AccountService accountService = new AccountServiceImpl();

    public Account getById(Long id) {
        return accountService.getById(id);
    }

    public List<Account> findAll() {
        return accountService.findAll();
    }

    public Account create(String name) {
        return accountService.save(name);
    }

    public Account update(Long id, AccountStatus accountStatus) {
        return accountService.update(id, accountStatus);
    }

    public void delete(Long id) {
        accountService.delete(id);
    }
}
