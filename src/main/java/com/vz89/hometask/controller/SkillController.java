package com.vz89.hometask.controller;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.service.SkillService;
import com.vz89.hometask.service.SkillServiceImpl;

import java.util.List;

public class SkillController {
    private SkillService skillService = new SkillServiceImpl();

    public Skill getById(Long readLong) {
        return skillService.getById(readLong);
    }

    public List<Skill> findAll() {
        return skillService.findAll();
    }

    public Skill create(String skillName) {
        return skillService.save(skillName);
    }

    public Skill update(Long id, String newName) {
        return skillService.update(id, newName);
    }

    public void delete(Long id) {
        skillService.delete(id);
    }
}
