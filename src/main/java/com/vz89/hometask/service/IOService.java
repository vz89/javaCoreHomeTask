package com.vz89.hometask.service;

import com.vz89.hometask.model.Skill;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IOService {
    private static Scanner sc = new Scanner(System.in);

    public static void write(String text) {
        System.out.println(text);
    }

    public static String read() {
        return sc.next();
    }

    public static Long readLong() {
        return sc.nextLong();
    }
    public static Set<Skill> parseSkillsStringToSet(String skillString, Map<Long, Skill> skillsMap) {
        Scanner scanner = new Scanner(skillString);
        Set<Skill> skills = new HashSet<>();
        if (!skillString.contains(",")) skills.add(skillsMap.get(Long.parseLong(skillString)));
        else {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                skills.add(skillsMap.get((scanner.nextLong())));
            }
        }
        return skills;
    }
}
