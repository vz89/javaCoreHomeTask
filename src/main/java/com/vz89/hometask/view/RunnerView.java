package com.vz89.hometask.view;

import com.vz89.hometask.service.IOService;
import com.vz89.hometask.service.RepositoryTypeService;

public class RunnerView {

    public void start() {
        System.out.println("Введите тип источника данных, с которым будете работать: db,json,io");
        String entity = IOService.read();
        RepositoryTypeService.setRepositoryType(entity);
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
