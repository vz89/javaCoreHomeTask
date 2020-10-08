package com.vz89.hometask.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonAccountRepositoryImpl implements AccountRepository {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String ACCOUNTS_JSON = "accounts.json";

    @Override
    public Account getById(Long id) {
        List<Account> accounts = getAccountsListFromJson();
        return accounts.stream().filter(account -> account.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Account> findAll() {
        return getAccountsListFromJson();
    }

    @Override
    public Account save(Account account) {
        List<Account> accounts = getAccountsListFromJson();
        if (accounts.isEmpty()) account.setId(1L);
        else account.setId(accounts.get(accounts.size() - 1).getId() + 1);
        account.setAccountStatus(AccountStatus.ACTIVE);
        accounts.add(account);
        writeJsonToFile(accounts);
        return account;
    }

    @Override
    public Account update(Account account) {
        List<Account> accounts = getAccountsListFromJson();
        writeJsonToFile(accounts.stream().map(s -> {
            if (s.getId().equals(account.getId())) {
                account.setName(s.getName());
                return account;
            }
            return s;
        }).collect(Collectors.toList()));
        return account;
    }

    @Override
    public void deleteById(Long id) {
        List<Account> accounts = getAccountsListFromJson();
        accounts.remove(getById(id));
        writeJsonToFile(accounts);
    }

    private List<Account> getAccountsListFromJson() {
        String skillsString = "";
        try {
            skillsString = Files.readString(Paths.get(ACCOUNTS_JSON));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't read accounts.json");
        }
        return gson.fromJson(skillsString, new TypeToken<List<Account>>() {
        }.getType());
    }

    private void writeJsonToFile(List<Account> accounts) {
        try {
            Files.writeString(Paths.get(ACCOUNTS_JSON), gson.toJson(accounts));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write in accounts.json");
        }
    }
}
