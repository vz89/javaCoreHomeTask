package com.vz89.hometask.service;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;

import java.util.List;

public interface AccountService {
    Account getById(Long id);

    List<Account> findAll();

    Account save(String name);

    Account update(Long id, AccountStatus accountStatus);

    void delete(Long id);

}
