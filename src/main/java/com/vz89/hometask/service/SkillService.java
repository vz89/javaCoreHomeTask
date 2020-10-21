package com.vz89.hometask.service;

import com.vz89.hometask.model.Skill;

import java.util.List;

public interface SkillService {
    Skill getById(Long readLong);

    List<Skill> findAll();

    Skill save(String skillName);

    Skill update(Long id, String newName);

    void delete(Long id);
}
