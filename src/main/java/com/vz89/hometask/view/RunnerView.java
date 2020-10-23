package com.vz89.hometask.view;

import com.vz89.hometask.repository.db.DbAccountRepositoryImpl;
import com.vz89.hometask.repository.db.DbDeveloperRepositoryImpl;
import com.vz89.hometask.repository.db.DbSkillRepositoryImpl;
import com.vz89.hometask.repository.io.IoAccountRepositoryImpl;
import com.vz89.hometask.repository.io.IoDeveloperRepositoryImpl;
import com.vz89.hometask.repository.io.IoSkillRepositoryImpl;
import com.vz89.hometask.repository.json.JsonAccountRepositoryImpl;
import com.vz89.hometask.repository.json.JsonDeveloperRepositoryImpl;
import com.vz89.hometask.repository.json.JsonSkillRepositoryImpl;
import com.vz89.hometask.service.IOService;
import com.vz89.hometask.service.RepositoryTypeService;

public class RunnerView {

    public void start() {
        System.out.println("Введите тип источника данных, с которым будете работать: db,json,io");
        String entity = IOService.read();
        if (entity.equals("db")) {
            RepositoryTypeService.setRepositoryType(new DbAccountRepositoryImpl(),new DbDeveloperRepositoryImpl(), new DbSkillRepositoryImpl());
        }
        if (entity.equals("json")) {
            RepositoryTypeService.setRepositoryType(new JsonAccountRepositoryImpl(),new JsonDeveloperRepositoryImpl(), new JsonSkillRepositoryImpl());
        }
        if (entity.equals("io")) {
            RepositoryTypeService.setRepositoryType(new IoAccountRepositoryImpl(),new IoDeveloperRepositoryImpl(), new IoSkillRepositoryImpl());
        }
        run();
    }

    public void run() {
        System.out.println("Введите сущность с которой будете работать: Skill, Account, Developer");
        String entity = IOService.read();
        if (entity.equals("Skill")) {
            SkillView skillView = new SkillView();
            skillView.run();
        }
        if (entity.equals("Account")) {
            AccountView accountView = new AccountView();
            accountView.run();
        }
        if (entity.equals("Developer")) {
            DeveloperView DeveloperView = new DeveloperView();
            DeveloperView.run();
        }
    }
}
