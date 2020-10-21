package com.vz89.hometask.controller;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.service.DeveloperService;
import com.vz89.hometask.service.DeveloperServiceImpl;

import java.util.List;

public class DeveloperController {
    private DeveloperService developerService = new DeveloperServiceImpl();

    public Developer getById(Long id) {
        return developerService.getById(id);
    }

    public List<Developer> findAll() {
        return developerService.findAll();
    }

    public void delete(Long id) {
        developerService.delete(id);
    }

    public Developer create(String firstName, String lastName, Long accountId, String skillString) {
        return developerService.save(firstName, lastName, accountId, skillString);
    }

    public Developer update(Long id, String firstName, String lastName) {
        return developerService.update(id, firstName, lastName);
    }

    public Developer updateSkill(Long id, String skillString) {
        return developerService.updateSkill(id, skillString);
    }
}
