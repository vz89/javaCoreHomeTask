package main.java.com.vz89.hometask.repository;

import main.java.com.vz89.hometask.model.Account;
import main.java.com.vz89.hometask.model.AccountStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository {

    private static final String ACCOUNTS_TXT = "accounts.txt";
    private static final String DELIMITER = ";";

    @Override
    public Account getById(Long id) {
        Account account = new Account();
        try {
            List<String> lines = Files.readAllLines(Paths.get(ACCOUNTS_TXT));
            Scanner scanner;
            for (String line : lines) {
                scanner = new Scanner(line);
                scanner.useDelimiter(DELIMITER);
                if (scanner.nextLong() == id) {
                    account.setId(id);
                    account.setName(scanner.next());
                    account.setAccountStatus(AccountStatus.valueOf(scanner.next()));
                    return account;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(ACCOUNTS_TXT));
            Scanner scanner;
            for (String line : lines) {
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                Long accountId = scanner.nextLong();
                String accountName = scanner.next();
                AccountStatus accountStatus = AccountStatus.valueOf(scanner.next());
                accounts.add(new Account(accountId, accountName, accountStatus));
            }
        } catch (IOException e) {
            System.out.println("Can't findAll Accounts from accounts.txt");
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account save(Account account) {
        Long id = getNewId();
        try {
            Files.writeString(Paths.get(ACCOUNTS_TXT), String.format("%n%d;%s;%s", id, account.getName(), AccountStatus.ACTIVE), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can't save Account to accounts.txt");
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account update(Account account) {
        List<Account> accounts = findAll().stream().map(account1 -> {
            if (account1.getId().equals(account.getId())) {
                account.setName(account1.getName());
                return account;
            } else return account1;
        }).collect(Collectors.toList());
        try {
            writeToFile(accounts);
        } catch (IOException e) {
            System.out.println("Can't update " + account + " Account to accounts.txt");
        }
        return account;
    }

    @Override
    public void deleteById(Long id) {
        List<Account> accounts = findAll().stream().filter(skill -> !skill.getId().equals(id)).collect(Collectors.toList());
        try {
            writeToFile(accounts);
        } catch (IOException e) {
            System.out.println("Can't delete " + id + " Account in accounts.txt");
        }
    }

    private Long getNewId() {
        Account account = findAll().stream().reduce((first, second) -> second).orElse(null);
        return account != null ? account.getId() + 1 : 1;
    }

    private void writeToFile(List<Account> accounts) throws IOException {
        Files.writeString(Paths.get(ACCOUNTS_TXT), "");
        accounts.forEach(account -> {
            try {
                Files.writeString(Paths.get(ACCOUNTS_TXT), String.format("%d;%s;%s%n", account.getId(), account.getName(), account.getAccountStatus()), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
