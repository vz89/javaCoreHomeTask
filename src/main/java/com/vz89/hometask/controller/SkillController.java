package com.vz89.hometask.controller;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.repository.SkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepository skillRepository = new SkillRepositoryImpl();

    public Skill getById(Long readLong) {
        return skillRepository.getById(readLong);
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill create(String skillName) {
        return skillRepository.save(new Skill(skillName));
    }

    public Skill update(Long id, String newName) {
        return skillRepository.update(new Skill(id, newName));
    }

    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
