package com.vz89.hometask;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.JsonSkillRepositoryImpl;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.service.IOService;
import com.vz89.hometask.view.AccountView;
import com.vz89.hometask.view.DeveloperView;
import com.vz89.hometask.view.SkillView;

public class HomeTaskApplication {
    public static void main(String[] args) {


        SkillRepository skillRepository = new JsonSkillRepositoryImpl();

        /*System.out.println("==================ADD==============================");

        skillRepository.save(new Skill("Pretty good"));*/

        System.out.println(skillRepository.findAll());

        System.out.println("==================Get by ID==============================");


        System.out.println(skillRepository.getById(2L));

        System.out.println("==================UPDATE ID==============================");


        skillRepository.update(new Skill(2L,"updated"));

        System.out.println(skillRepository.findAll());

        System.out.println("==================Delete by ID==============================");

        skillRepository.deleteById(1L);

        System.out.println(skillRepository.findAll());



       /* System.out.println("Введите сущность с которой будете работать: Skill, Account, Developer");
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
        }*/

    }
}
