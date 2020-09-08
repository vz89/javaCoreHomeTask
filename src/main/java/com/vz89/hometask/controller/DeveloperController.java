package main.java.com.vz89.hometask.controller;

import main.java.com.vz89.hometask.model.Developer;
import main.java.com.vz89.hometask.model.Skill;
import main.java.com.vz89.hometask.repository.*;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
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
        developer.setSkills(parseSkillsStringToSet(skillString));
        return developerRepository.save(developer);
    }

    private Set<Skill> parseSkillsStringToSet(String skillString) {
        Set<Skill> skills = new HashSet<>();
        Scanner scanner = new Scanner(skillString);
        if (!skillString.contains(",")) skills.add(skillRepository.getById(Long.parseLong(skillString)));
        else {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                skills.add(skillRepository.getById(scanner.nextLong()));
            }
        }
        return skills;
    }

    public Developer update(Long id, String firstName, String lastName) {
        Developer developer = developerRepository.getById(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        return developerRepository.update(developer);
    }

    public Developer updateSkill(Long id, String skillString) {
        Developer developer = developerRepository.getById(id);
        Set<Skill> skills = parseSkillsStringToSet(skillString);
        developer.setSkills(skills);
        return developerRepository.update(developer);
    }
}
