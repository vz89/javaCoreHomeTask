package main.java.com.vz89.hometask;

import main.java.com.vz89.hometask.service.IOService;
import main.java.com.vz89.hometask.view.AccountView;
import main.java.com.vz89.hometask.view.DeveloperView;
import main.java.com.vz89.hometask.view.SkillView;

public class HomeTaskApplication {
    public static void main(String[] args) {
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
