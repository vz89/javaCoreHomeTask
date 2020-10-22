package com.vz89.hometask.service;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.repository.db.DbAccountRepositoryImpl;
import com.vz89.hometask.repository.db.DbDeveloperRepositoryImpl;
import com.vz89.hometask.repository.db.DbSkillRepositoryImpl;
import com.vz89.hometask.repository.json.JsonAccountRepositoryImpl;
import com.vz89.hometask.repository.json.JsonDeveloperRepositoryImpl;
import com.vz89.hometask.repository.json.JsonSkillRepositoryImpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperServiceImpl implements DeveloperService {
    private DeveloperRepository developerRepository = new DbDeveloperRepositoryImpl();
    private AccountRepository accountRepository = new DbAccountRepositoryImpl();
    private SkillRepository skillRepository = new DbSkillRepositoryImpl();

    @Override
    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }

    @Override
    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    @Override
    public Developer save(String firstName, String lastName, Long accountId, String skillString) {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setAccount(accountRepository.getById(accountId));
        developer.setSkills(IOService.parseSkillsStringToSet(skillString, skillRepository.findAll().stream().collect(Collectors.toMap(Skill::getId, s -> s))));
        return developerRepository.save(developer);
    }

    @Override
    public Developer update(Long id, String firstName, String lastName) {
        Developer developer = developerRepository.getById(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        return developerRepository.update(developer);
    }

    @Override
    public Developer updateSkill(Long id, String skillString) {
        Developer developer = developerRepository.getById(id);
        Set<Skill> skills = IOService.parseSkillsStringToSet(skillString, skillRepository.findAll().stream().collect(Collectors.toMap(Skill::getId, s -> s)));
        developer.setSkills(skills);
        return developerRepository.update(developer);
    }
}
