package main.java.com.vz89.hometask.controller;

import main.java.com.vz89.hometask.model.Skill;
import main.java.com.vz89.hometask.repository.GenericRepository;
import main.java.com.vz89.hometask.repository.SkillRepository;

import java.util.List;

public class SkillController {
    private GenericRepository<Skill, Long> genericRepository = new SkillRepository();

    public Skill getById(Long readLong) {
        return genericRepository.getById(readLong);
    }

    public List<Skill> findAll() {
        return genericRepository.findAll();
    }

    public Skill create(String skillName) {
        return genericRepository.save(new Skill(skillName));
    }

    public Skill update(Long id, String newName) {
        return genericRepository.update(new Skill(id,newName));
    }

    public void delete(Long id) {
        genericRepository.deleteById(id);
    }
}
