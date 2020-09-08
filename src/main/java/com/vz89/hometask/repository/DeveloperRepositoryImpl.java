package main.java.com.vz89.hometask.repository;

import main.java.com.vz89.hometask.model.Account;
import main.java.com.vz89.hometask.model.AccountStatus;
import main.java.com.vz89.hometask.model.Developer;
import main.java.com.vz89.hometask.model.Skill;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
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
                    developer.setSkills(parseSkills(scanner.next()));
                    return developer;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Set<Skill> parseSkills(String next) {
        Set<Skill> skills = new HashSet<>();
        Scanner scanner = new Scanner(next);
        scanner.useDelimiter(",");
        while(scanner.hasNext()) {
            skills.add(skillRepository.getById(scanner.nextLong()));
        }
        return skills;
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
                developer.setSkills(parseSkills(scanner.next()));
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
            Files.writeString(Paths.get(DEVELOPER_TXT), String.format("%n%d;%s;%s;%s;%s", id,developer.getFirstName(), developer.getLastName(), developer.getAccount().getId(),parseSkillsToString(developer.getSkills()) ), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can't save Account to accounts.txt");
            e.printStackTrace();
        }
        return developer;
    }

    private String parseSkillsToString(Set<Skill> skills) {
        return skills.stream().map(skill -> skill.getId().toString()).collect(Collectors.joining(","));
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
    private Long getNewId() {
        Developer developer = findAll().stream().reduce((first, second) -> second).orElse(null);
        return developer != null ? developer.getId() + 1 : 1;
    }
}
