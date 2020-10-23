package com.vz89.hometask.repository.io;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IoSkillRepositoryImpl implements SkillRepository {

    private static final String SKILLS_TXT = "/files/skills.txt";
    private static final String DELIMITER = ";";

    @Override
    public Skill getById(Long id) {
        Skill skill = new Skill();
        try {
            List<String> lines = Files.readAllLines(Paths.get(getUri()));
            Scanner scanner;
            for (String line : lines) {
                scanner = new Scanner(line);
                scanner.useDelimiter(DELIMITER);
                if (scanner.nextLong() == id) {
                    skill.setId(id);
                    skill.setName(scanner.next());
                    return skill;
                }
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Can't getById Skill from skills.txt");
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public List<Skill> findAll() {
        List<Skill> skills = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(getUri()));
            Scanner scanner;
            for (String line : lines) {
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                Long skillId = scanner.nextLong();
                String skillName = scanner.next();
                skills.add(new Skill(skillId, skillName));
            }

        } catch (IOException | URISyntaxException e) {
            System.out.println("Can't findAll Skills from skills.txt");
            e.printStackTrace();
        }
        return skills;
    }

    @Override
    public Skill save(Skill skill) {
        Long id = getNewId();
        try {
            Files.writeString(Paths.get(getUri()), String.format("%n%d;%s", id, skill.getName()), StandardOpenOption.APPEND);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Can't save Skill to skills.txt");
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> skills = findAll().stream().map(skill1 -> {
            if (skill1.getId().equals(skill.getId())) return skill;
            else return skill1;
        }).collect(Collectors.toList());
        try {
            writeToFile(skills);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Can't update " + skill + " Skill to skills.txt");
        }
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> skills = findAll().stream().filter(skill -> !skill.getId().equals(id)).collect(Collectors.toList());
        try {
            writeToFile(skills);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Can't delete " + id + " Skill to skills.txt");
        }

    }

    private void writeToFile(List<Skill> skills) throws IOException, URISyntaxException {
        Files.writeString(Paths.get(getUri()), "");
        skills.forEach(sk -> {
            try {
                Files.writeString(Paths.get(getUri()), String.format("%d;%s%n", sk.getId(), sk.getName()), StandardOpenOption.APPEND);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

    private Long getNewId() {
        Skill skill = findAll().stream().reduce((first, second) -> second).orElse(null);
        return skill != null ? skill.getId() + 1 : 1;
    }

    private URI getUri() throws URISyntaxException {
        return IoSkillRepositoryImpl.class.getResource(SKILLS_TXT).toURI();
    }
}
