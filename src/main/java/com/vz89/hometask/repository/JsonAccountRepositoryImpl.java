package com.vz89.hometask.repository;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.service.JsonService;

import java.util.List;
import java.util.stream.Collectors;

public class JsonAccountRepositoryImpl implements AccountRepository {
    private static final String ACCOUNTS_JSON = "accounts.json";

    @Override
    public Account getById(Long id) {
        List<Account> accounts = JsonService.getListFromJson(ACCOUNTS_JSON, Account.class);
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Account> findAll() {
        return JsonService.getListFromJson(ACCOUNTS_JSON, Account.class);
    }

    @Override
    public Account save(Account account) {
        List<Account> accounts = JsonService.getListFromJson(ACCOUNTS_JSON, Account.class);
        if (accounts.isEmpty()) account.setId(1L);
        else account.setId(accounts.get(accounts.size() - 1).getId() + 1);
        account.setAccountStatus(AccountStatus.ACTIVE);
        accounts.add(account);
        JsonService.writeJsonToFile(accounts, ACCOUNTS_JSON);
        return account;
    }

    @Override
    public Account update(Account account) {
        List<Account> accounts = JsonService.getListFromJson(ACCOUNTS_JSON, Account.class);
        JsonService.writeJsonToFile(accounts.stream().map(s -> {
            if (s.getId().equals(account.getId())) {
                account.setName(s.getName());
                return account;
            }
            return s;
        }).collect(Collectors.toList()), ACCOUNTS_JSON);
        return account;
    }

    @Override
    public void deleteById(Long id) {
        List<Account> accounts = JsonService.getListFromJson(ACCOUNTS_JSON, Account.class);
        accounts.remove(getById(id));
        JsonService.writeJsonToFile(accounts, ACCOUNTS_JSON);
    }
}
