package com.vz89.hometask.repository.json;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.service.JsonService;

import java.util.List;
import java.util.stream.Collectors;

public class JsonSkillRepositoryImpl implements SkillRepository {
    private static final String SKILLS_JSON = "/skills.json";

    @Override
    public Skill getById(Long id) {
        List<Skill> skills = JsonService.getListFromJson(SKILLS_JSON, Skill.class);
        return skills.stream().filter(skill -> skill.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Skill> findAll() {
        return JsonService.getListFromJson(SKILLS_JSON, Skill.class);
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> skills = JsonService.getListFromJson(SKILLS_JSON, Skill.class);
        if (skills.isEmpty()) skill.setId(1L);
        else skill.setId(skills.get(skills.size() - 1).getId() + 1);
        skills.add(skill);
        JsonService.writeJsonToFile(skills, SKILLS_JSON);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> skills = JsonService.getListFromJson(SKILLS_JSON, Skill.class);
        JsonService.writeJsonToFile(skills.stream().map(s -> s.getId().equals(skill.getId()) ? skill : s).collect(Collectors.toList()), SKILLS_JSON);
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> skills = JsonService.getListFromJson(SKILLS_JSON, Skill.class);
        skills.remove(getById(id));
        JsonService.writeJsonToFile(skills, SKILLS_JSON);
    }

}
