package com.vz89.hometask.service;

import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.repository.db.DbAccountRepositoryImpl;
import com.vz89.hometask.repository.db.DbDeveloperRepositoryImpl;
import com.vz89.hometask.repository.db.DbSkillRepositoryImpl;
import com.vz89.hometask.repository.io.IoAccountRepositoryImpl;
import com.vz89.hometask.repository.io.IoDeveloperRepositoryImpl;
import com.vz89.hometask.repository.io.IoSkillRepositoryImpl;
import com.vz89.hometask.repository.json.JsonAccountRepositoryImpl;
import com.vz89.hometask.repository.json.JsonDeveloperRepositoryImpl;
import com.vz89.hometask.repository.json.JsonSkillRepositoryImpl;

public class RepositoryTypeService {
    private static AccountRepository accountRepository;
    private static DeveloperRepository developerRepository;
    private static SkillRepository skillRepository;

    public static void setRepositoryType(String type) {
        switch (type) {
            case ("db"):
                RepositoryTypeService.accountRepository = new DbAccountRepositoryImpl();
                RepositoryTypeService.developerRepository = new DbDeveloperRepositoryImpl();
                RepositoryTypeService.skillRepository = new DbSkillRepositoryImpl();
                break;
            case ("json"):
                RepositoryTypeService.accountRepository = new JsonAccountRepositoryImpl();
                RepositoryTypeService.developerRepository = new JsonDeveloperRepositoryImpl();
                RepositoryTypeService.skillRepository = new JsonSkillRepositoryImpl();
                break;
            case ("io"):
                RepositoryTypeService.accountRepository = new IoAccountRepositoryImpl();
                RepositoryTypeService.developerRepository = new IoDeveloperRepositoryImpl();
                RepositoryTypeService.skillRepository = new IoSkillRepositoryImpl();
                break;
            default:
                RepositoryTypeService.accountRepository = new DbAccountRepositoryImpl();
                RepositoryTypeService.developerRepository = new DbDeveloperRepositoryImpl();
                RepositoryTypeService.skillRepository = new DbSkillRepositoryImpl();
                break;
        }
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
