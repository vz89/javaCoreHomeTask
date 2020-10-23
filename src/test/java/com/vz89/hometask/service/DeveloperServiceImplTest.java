package com.vz89.hometask.service;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceImplTest {

    @Mock
    AccountRepository accountRepository;
    @Mock
    DeveloperRepository developerRepository;
    @Mock
    SkillRepository skillRepository;


    @Test
    void shouldCorrectGetById() {
        DeveloperServiceImpl developerService = new DeveloperServiceImpl(developerRepository, accountRepository, skillRepository);
        Developer expectedDeveloper = new Developer(1L, "firstname", "lastname", new HashSet<>() {{
            new Skill(1L, "php");
            new Skill(2L, "java");
        }}, new Account(1L, "account", AccountStatus.ACTIVE));
        Mockito.when(developerRepository.getById(1L)).thenReturn(new Developer(1L, "firstname", "lastname", new HashSet<>() {{
            new Skill(1L, "php");
            new Skill(2L, "java");
        }}, new Account(1L, "account", AccountStatus.ACTIVE)));
        assertEquals(expectedDeveloper, developerService.getById(1L));
    }

    @Test
    void shouldFindAll() {
        DeveloperServiceImpl developerService = new DeveloperServiceImpl(developerRepository, accountRepository, skillRepository);
        Developer expectedDeveloper1 = new Developer(1L, "firstname", "lastname", new HashSet<>() {{
            new Skill(1L, "php");
            new Skill(2L, "java");
        }}, new Account(1L, "account", AccountStatus.ACTIVE));
        Developer expectedDeveloper2 = new Developer(1L, "firstname", "lastname", new HashSet<>() {{
            new Skill(1L, "php");
            new Skill(2L, "java");
        }}, new Account(1L, "account", AccountStatus.ACTIVE));
        List<Developer> list = new ArrayList<>();
        list.add(expectedDeveloper1);
        list.add(expectedDeveloper2);

        Mockito.when(developerRepository.findAll()).thenReturn(list);
        assertEquals(list, developerService.findAll());
    }

    @Test
    void shouldCorrectSave() {
        DeveloperServiceImpl developerService = new DeveloperServiceImpl(developerRepository, accountRepository, skillRepository);
        Developer expectedDeveloper = new Developer(1L, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))), new Account(1L, "account", AccountStatus.ACTIVE));

        Mockito.when(accountRepository.getById(1L)).thenReturn(new Account(1L, "account", AccountStatus.ACTIVE));
        Mockito.when(skillRepository.findAll()).thenReturn(new ArrayList<Skill>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))));
        Mockito.when(developerRepository.save(new Developer(null, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))),
                new Account(1L, "account", AccountStatus.ACTIVE))))
                .thenReturn(new Developer(1L, "firstname", "lastname",
                        new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))),
                        new Account(1L, "account", AccountStatus.ACTIVE)));
        assertEquals(expectedDeveloper, developerService.save("firstname", "lastname", 1L, "1,2"));
    }

    @Test
    void shouldCorrectUpdate() {
        DeveloperServiceImpl developerService = new DeveloperServiceImpl(developerRepository, accountRepository, skillRepository);
        Developer expectedDeveloper = new Developer(1L, "newfirstname", "newlastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))), new Account(1L, "account", AccountStatus.ACTIVE));

        Mockito.when(developerRepository.getById(1L)).thenReturn(new Developer(1L, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))), new Account(1L, "account", AccountStatus.ACTIVE)));
        Mockito.when(developerRepository.update(new Developer(1L, "newfirstname", "newlastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))), new Account(1L, "account", AccountStatus.ACTIVE))))
                .thenReturn(new Developer(1L, "newfirstname", "newlastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))), new Account(1L, "account", AccountStatus.ACTIVE)));

        assertEquals(expectedDeveloper, developerService.update(1L, "newfirstname", "newlastname"));
    }

    @Test
    void shouldCorrectUpdateSkill() {
        DeveloperServiceImpl developerService = new DeveloperServiceImpl(developerRepository, accountRepository, skillRepository);
        Developer expectedDeveloper = new Developer(1L, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(3L, "html"), new Skill(4L, "c#"))), new Account(1L, "account", AccountStatus.ACTIVE));
        Mockito.when(developerRepository.getById(1L)).thenReturn(new Developer(1L, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(1L, "php"), new Skill(2L, "java"))), new Account(1L, "account", AccountStatus.ACTIVE)));
        Mockito.when(skillRepository.findAll()).thenReturn(new ArrayList<Skill>(Arrays.asList(new Skill(3L, "html"), new Skill(4L, "c#"))));
        Mockito.when(developerRepository.update(new Developer(1L, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(3L, "html"), new Skill(4L, "c#"))), new Account(1L, "account", AccountStatus.ACTIVE))))
                .thenReturn(new Developer(1L, "firstname", "lastname", new HashSet<>(Arrays.asList(new Skill(3L, "html"), new Skill(4L, "c#"))), new Account(1L, "account", AccountStatus.ACTIVE)));
        assertEquals(expectedDeveloper, developerService.updateSkill(1L, "3,4"));
    }

    @Test
    void shouldCorrectDelete() {

    }
}