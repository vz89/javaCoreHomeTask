package main.java.com.vz89.hometask;

import main.java.com.vz89.hometask.service.IOService;
import main.java.com.vz89.hometask.view.SkillView;

public class HomeTaskApplication {
    public static void main(String[] args) {
        /*GenericRepository<Skill, Long> genericRepository = new SkillRepository();
        System.out.println(genericRepository.findAll());
        System.out.println("get by id");

        Skill skill = genericRepository.getById((long)4);
        System.out.println(skill);

        System.out.println();
        skill.setName("new skill");
        genericRepository.update(skill);
        System.out.println(genericRepository.findAll());
        System.out.println();

        genericRepository.deleteById((long)5);
        System.out.println(genericRepository.findAll());
        System.out.println();

        Skill skill1 = new Skill("SQL");
        genericRepository.save(skill1);
        System.out.println(genericRepository.findAll());
        System.out.println();*/

        System.out.println("Введите сущность с которой будете работать: Skill, Account(в разработке), Developer(в разработке) ");
        String entity = IOService.read();
        if (entity.equals("Skill")) {
            SkillView skillView = new SkillView();
            skillView.run();
        }


    }
}
