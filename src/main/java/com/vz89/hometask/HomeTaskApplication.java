package com.vz89.hometask;

import com.vz89.hometask.service.IOService;
import com.vz89.hometask.view.AccountView;
import com.vz89.hometask.view.DeveloperView;
import com.vz89.hometask.view.SkillView;

public class HomeTaskApplication {
    public static void main(String[] args) {
        /*HashSet<Skill> skills = new HashSet<>();
        skills.add(new Skill(1L,"php"));
        skills.add(new Skill(2L,"java"));
        skills.add(new Skill(3L,"c#"));
        Account account = new Account(1L,"account", AccountStatus.ACTIVE);

        Developer developer = new Developer(1L,skills);
        developer.setFirstName("Vova");
        developer.setLastName("Bob");
        developer.setAccount(account);

        Long[] longs1 = developer.getSkills().stream().map(Skill::getId).collect(Collectors.toList()).toArray(Long[]::new);



        DeveloperDTO developerDTO = DeveloperMapper.developerDTOtoJson(developer);
        System.out.println(developerDTO);*/

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
