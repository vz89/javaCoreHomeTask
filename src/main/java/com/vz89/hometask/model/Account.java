package main.java.com.vz89.hometask.model;

import java.util.Objects;

public class Account {
    private Long id;
    private String name;
    private AccountStatus accountStatus;


    public Account(Long id, String name, AccountStatus accountStatus) {
        this.id = id;
        this.accountStatus = accountStatus;
        this.name = name;
    }

    public Account(Long id, AccountStatus accountStatus) {
        this.id = id;
        this.accountStatus = accountStatus;
    }

    public Account(String name) {
        this.name = name;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                accountStatus == account.accountStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, accountStatus);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
