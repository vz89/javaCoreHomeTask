package com.vz89.hometask.service;

import com.vz89.hometask.model.Developer;

import java.util.List;

public interface DeveloperService {
    Developer getById(Long id);

    List<Developer> findAll();

    void delete(Long id);

    Developer save(String firstName, String lastName, Long accountId, String skillString);


    Developer update(Long id, String firstName, String lastName);

    Developer updateSkill(Long id, String skillString);
}
