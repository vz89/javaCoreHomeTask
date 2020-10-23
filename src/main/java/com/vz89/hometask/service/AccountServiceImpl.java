package com.vz89.hometask.service;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.repository.AccountRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        accountRepository = RepositoryTypeService.getAccountRepoType();
    }

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.getById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(String name) {
        return accountRepository.save(new Account(name));
    }

    @Override
    public Account update(Long id, AccountStatus accountStatus) {
        return accountRepository.update(new Account(id, accountStatus));
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

}
