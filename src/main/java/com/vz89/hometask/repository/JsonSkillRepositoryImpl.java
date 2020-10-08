package com.vz89.hometask.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vz89.hometask.model.Skill;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonSkillRepositoryImpl implements SkillRepository {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String SKILLS_JSON = "skills.json";

    @Override
    public Skill getById(Long id) {
        List<Skill> skills = getSkillsListFromJson();
        return skills.stream().filter(skill -> skill.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Skill> findAll() {
        return getSkillsListFromJson();
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> skills = getSkillsListFromJson();
        skill.setId(skills.get(skills.size() - 1).getId() + 1);
        skills.add(skill);
        writeJsonToFile(skills);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> skills = getSkillsListFromJson();
        writeJsonToFile(skills.stream().map(s -> s.getId().equals(skill.getId()) ? skill : s).collect(Collectors.toList()));
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> skills = getSkillsListFromJson();
        skills.remove(getById(id));
        writeJsonToFile(skills);
    }

    private List<Skill> getSkillsListFromJson() {
        String skillsString = "";
        try {
            skillsString = Files.readString(Paths.get(SKILLS_JSON));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't read skills.json");
        }
        return gson.fromJson(skillsString, new TypeToken<List<Skill>>() {
        }.getType());
    }

    private void writeJsonToFile(List<Skill> skills) {
        try {
            Files.writeString(Paths.get(SKILLS_JSON), gson.toJson(skills));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write in skills.json");
        }
    }
}
