package main.java.com.vz89.hometask.controller;

import main.java.com.vz89.hometask.model.AccountStatus;
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



    public Developer update(Long id, AccountStatus accountStatus) {
        return null;
    }

    public void delete(Long id) {

    }

    public Developer create(String firstName, String lastName, Long accountId, String skillString) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setAccount(accountRepository.getById(accountId));
        developer.setSkills(parseSkills(skillString));
        return developerRepository.save(developer);
    }
    private Set<Skill> parseSkills(String skillString) {
        Set<Skill> skills = new HashSet<>();
        Scanner scanner = new Scanner(skillString);
        scanner.useDelimiter(",");
        while(scanner.hasNext()) {
            skills.add(skillRepository.getById(scanner.nextLong()));
        }
        return skills;
    }
}
