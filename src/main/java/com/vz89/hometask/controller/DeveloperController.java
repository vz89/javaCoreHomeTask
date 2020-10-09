package com.vz89.hometask.controller;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.*;
import com.vz89.hometask.repository.json.JsonAccountRepositoryImpl;
import com.vz89.hometask.repository.json.JsonDeveloperRepositoryImpl;
import com.vz89.hometask.repository.json.JsonSkillRepositoryImpl;
import com.vz89.hometask.service.IOService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperController {
    private DeveloperRepository developerRepository = new JsonDeveloperRepositoryImpl();
    private AccountRepository accountRepository = new JsonAccountRepositoryImpl();
    private SkillRepository skillRepository = new JsonSkillRepositoryImpl();


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
        developer.setSkills(IOService.parseSkillsStringToSet(skillString, skillRepository.findAll().stream().collect(Collectors.toMap(Skill::getId, s -> s))));
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
        Set<Skill> skills = IOService.parseSkillsStringToSet(skillString, skillRepository.findAll().stream().collect(Collectors.toMap(Skill::getId, s -> s)));
        developer.setSkills(skills);
        return developerRepository.update(developer);
    }
}
