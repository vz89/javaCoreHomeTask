package com.vz89.hometask.service;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;

import java.util.List;

public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    public SkillServiceImpl() {
        skillRepository = RepositoryTypeService.getSkillRepoType();
    }

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.getById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill save(String skillName) {
        return skillRepository.save(new Skill(skillName));
    }

    @Override
    public Skill update(Long id, String newName) {
        return skillRepository.update(new Skill(id, newName));
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}
