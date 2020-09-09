package main.java.com.vz89.hometask.utils;

import main.java.com.vz89.hometask.model.Skill;
import main.java.com.vz89.hometask.repository.SkillRepository;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class IOUtils {

    public static Set<Skill> parseSkillsStringToSet(String skillString, SkillRepository skillRepository) {
        Set<Skill> skills = new HashSet<>();
        Scanner scanner = new Scanner(skillString);
        if (!skillString.contains(",")) skills.add(skillRepository.getById(Long.parseLong(skillString)));
        else {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                skills.add(skillRepository.getById(scanner.nextLong()));
            }
        }
        return skills;
    }
}
