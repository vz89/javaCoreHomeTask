package com.vz89.hometask.service;

import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.repository.SkillRepository;

public class RepositoryTypeService {
    private static AccountRepository accountRepository;
    private static DeveloperRepository developerRepository;
    private static SkillRepository skillRepository;

    public static void setRepositoryType(AccountRepository accountRepository, DeveloperRepository developerRepository, SkillRepository skillRepository) {
        RepositoryTypeService.accountRepository = accountRepository;
        RepositoryTypeService.developerRepository = developerRepository;
        RepositoryTypeService.skillRepository = skillRepository;
    }

    public static AccountRepository getAccountRepoType() {
        return accountRepository;
    }

    public static DeveloperRepository getDeveloperRepoType() {
        return developerRepository;
    }

    public static SkillRepository getSkillRepoType() {
        return skillRepository;
    }
}
