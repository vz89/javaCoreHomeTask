package com.vz89.hometask.repository.json;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.service.JsonService;

import java.util.List;
import java.util.stream.Collectors;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {
    private static final String DEVELOPERS_JSON = "/developers.json";

    @Override
    public Developer getById(Long id) {
        List<Developer> developers = JsonService.getListFromJson(DEVELOPERS_JSON, Developer.class);
        return developers.stream().filter(developer -> developer.getId().equals(id)).findFirst().get();
    }

    @Override
    public List<Developer> findAll() {
        return JsonService.getListFromJson(DEVELOPERS_JSON, Developer.class);
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> developers = JsonService.getListFromJson(DEVELOPERS_JSON, Developer.class);
        if (developers.isEmpty()) developer.setId(1L);
        else developer.setId(developers.get(developers.size() - 1).getId() + 1);
        developers.add(developer);
        JsonService.writeJsonToFile(developers, DEVELOPERS_JSON);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> developers = JsonService.getListFromJson(DEVELOPERS_JSON, Developer.class);
        JsonService.writeJsonToFile(developers.stream().map(s -> s.getId().equals(developer.getId()) ? developer : s).collect(Collectors.toList()), DEVELOPERS_JSON);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> developers = JsonService.getListFromJson(DEVELOPERS_JSON, Developer.class);
        developers.remove(getById(id));
        JsonService.writeJsonToFile(developers, DEVELOPERS_JSON);
    }
}
