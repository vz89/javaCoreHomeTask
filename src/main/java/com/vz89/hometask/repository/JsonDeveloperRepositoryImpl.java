package com.vz89.hometask.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.vz89.hometask.model.Developer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String DEVELOPERS_JSON = "developers.json";

    @Override
    public Developer getById(Long id) {
        List<Developer> developers = getDevelopersListFromJson();
        return developers.stream().filter(developer -> developer.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Developer> findAll() {
        return getDevelopersListFromJson();
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> developers = getDevelopersListFromJson();
        if (developers.isEmpty()) developer.setId(1L);
        else developer.setId(developers.get(developers.size() - 1).getId() + 1);
        developers.add(developer);
        writeJsonToFile(developers);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> developers = getDevelopersListFromJson();
        writeJsonToFile(developers.stream().map(s -> s.getId().equals(developer.getId()) ? developer : s).collect(Collectors.toList()));
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> developers = getDevelopersListFromJson();
        developers.remove(getById(id));
        writeJsonToFile(developers);
    }

    private List<Developer> getDevelopersListFromJson() {
        String skillsString = "";
        try {
            skillsString = Files.readString(Paths.get(DEVELOPERS_JSON));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't read developers.json");
        }
        return gson.fromJson(skillsString, new TypeToken<List<Developer>>() {
        }.getType());
    }

    private void writeJsonToFile(List<Developer> developers) {
        try {
            Files.writeString(Paths.get(DEVELOPERS_JSON), gson.toJson(developers));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't write in developers.json");
        }
    }
}
