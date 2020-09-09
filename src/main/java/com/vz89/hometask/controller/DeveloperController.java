package main.java.com.vz89.hometask.controller;

import main.java.com.vz89.hometask.model.Developer;
import main.java.com.vz89.hometask.model.Skill;
import main.java.com.vz89.hometask.repository.*;
import main.java.com.vz89.hometask.utils.IOUtils;

import java.util.List;
import java.util.Set;

public class DeveloperController {
    private DeveloperRepository developerRepository = new DeveloperRepositoryImpl();
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private SkillRepository skillRepository = new SkillRepositoryImpl();


    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }


    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    public Developer create(String firstName, String lastName, Long accountId, String skillString) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setAccount(accountRepository.getById(accountId));
        developer.setSkills(IOUtils.parseSkillsStringToSet(skillString,skillRepository));
        return developerRepository.save(developer);
    }



    public Developer update(Long id, String firstName, String lastName) {
        Developer developer = developerRepository.getById(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        return developerRepository.update(developer);
    }

    public Developer updateSkill(Long id, String skillString) {
        Developer developer = developerRepository.getById(id);
        Set<Skill> skills = IOUtils.parseSkillsStringToSet(skillString,skillRepository);
        developer.setSkills(skills);
        return developerRepository.update(developer);
    }
}
