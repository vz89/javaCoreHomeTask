package com.vz89.hometask.repository;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.service.IOService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private SkillRepository skillRepository = new SkillRepositoryImpl();

    private static final String DEVELOPER_TXT = "developers.txt";
    private static final String DELIMITER = ";";

    @Override
    public Developer getById(Long id) {
        Developer developer = new Developer();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DEVELOPER_TXT));
            Scanner scanner;
            for (String line : lines) {
                scanner = new Scanner(line);
                scanner.useDelimiter(DELIMITER);
                if (scanner.nextLong() == id) {
                    developer.setId(id);
                    developer.setFirstName(scanner.next());
                    developer.setLastName(scanner.next());
                    developer.setAccount(accountRepository.getById(scanner.nextLong()));
                    developer.setSkills(IOService.parseSkillsStringToSet(scanner.next(), skillRepository.findAll().stream().collect(Collectors.toMap(Skill::getId, s -> s))));
                    return developer;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Developer> findAll() {
        List<Developer> Developer = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DEVELOPER_TXT));
            Scanner scanner;
            for (String line : lines) {
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                Developer developer = new Developer();
                developer.setId(scanner.nextLong());
                developer.setFirstName(scanner.next());
                developer.setLastName(scanner.next());
                developer.setAccount(accountRepository.getById(scanner.nextLong()));
                developer.setSkills(IOService.parseSkillsStringToSet(scanner.next(), skillRepository.findAll().stream().collect(Collectors.toMap(Skill::getId, s -> s))));
                Developer.add(developer);
            }
        } catch (IOException e) {
            System.out.println("Can't findAll Developer from developers.txt");
            e.printStackTrace();
        }
        return Developer;
    }

    @Override
    public Developer save(Developer developer) {
        Long id = getNewId();
        try {
            Files.writeString(Paths.get(DEVELOPER_TXT), String.format("%n%d;%s;%s;%s;%s", id, developer.getFirstName(), developer.getLastName(), developer.getAccount().getId(), parseSkillsSetToString(developer.getSkills())), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can't save Developer to developers.txt");
            e.printStackTrace();
        }
        return developer;
    }

    private String parseSkillsSetToString(Set<Skill> skills) {
        return skills.stream().map(skill -> skill.getId().toString()).collect(Collectors.joining(","));
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> developers = findAll().stream().map(developer1 -> {
            if (developer1.getId().equals(developer.getId())) {
                return developer;
            } else return developer1;
        }).collect(Collectors.toList());
        try {
            writeToFile(developers);
        } catch (IOException e) {
            System.out.println("Can't update " + developer + " Developer to developers.txt");
        }
        return developer;
    }

    private void writeToFile(List<Developer> developers) throws IOException {
        Files.writeString(Paths.get(DEVELOPER_TXT), "");
        developers.forEach(developer -> {
            try {
                Files.writeString(Paths.get(DEVELOPER_TXT), String.format("%d;%s;%s;%s;%s%n", developer.getId(), developer.getFirstName(), developer.getLastName(), developer.getAccount().getId(), parseSkillsSetToString(developer.getSkills())), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> developers = findAll().stream().filter(skill -> !skill.getId().equals(id)).collect(Collectors.toList());
        try {
            writeToFile(developers);
        } catch (IOException e) {
            System.out.println("Can't delete " + id + " Developer in developers.txt");
        }
    }

    private Long getNewId() {
        Developer developer = findAll().stream().reduce((first, second) -> second).orElse(null);
        return developer != null ? developer.getId() + 1 : 1;
    }

}


