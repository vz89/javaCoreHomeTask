package com.vz89.hometask.service;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    AccountRepository accountRepository;

    @Test
    void shouldCorrectGetById() {
        AccountService accountService = new AccountServiceImpl(accountRepository);
        Account expectedAccount = new Account(1L, "account", AccountStatus.ACTIVE);
        Mockito.when(accountRepository.getById(1L)).thenReturn(new Account(1L, "account", AccountStatus.ACTIVE));
        assertEquals(expectedAccount, accountService.getById(1L));
    }

    @Test
    void shouldFindAll() {
        AccountService accountService = new AccountServiceImpl(accountRepository);
        List<Account> accountList = new ArrayList<>() {{
            new Account(1L, "account1", AccountStatus.ACTIVE);
            new Account(2L, "account2", AccountStatus.ACTIVE);
        }};
        Mockito.when(accountRepository.findAll()).thenReturn(new ArrayList<>() {{
            new Account(1L, "account1", AccountStatus.ACTIVE);
            new Account(2L, "account2", AccountStatus.ACTIVE);
        }});
        assertEquals(accountList, accountService.findAll());
    }

    @Test
    void shouldCorrectSave() {
        AccountService accountService = new AccountServiceImpl(accountRepository);
        Account expectedAccount = new Account(1L, "name", AccountStatus.ACTIVE);
        Mockito.when(accountRepository.save(new Account(null, "name", null))).thenReturn(new Account(1L, "name", AccountStatus.ACTIVE));
        assertEquals(expectedAccount, accountService.save("name"));
    }

    @Test
    void shouldCorrectUpdate() {
        AccountService accountService = new AccountServiceImpl(accountRepository);
        Account expectedAccount = new Account(1L, "newname", AccountStatus.BANNED);
        Mockito.when(accountRepository.update(new Account(1L, AccountStatus.BANNED))).thenReturn(new Account(1L, "newname", AccountStatus.BANNED));
        assertEquals(expectedAccount, accountService.update(1L, AccountStatus.BANNED));
    }

    @Test
    void shouldCorrectDelete() {
    }
}