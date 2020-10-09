package com.vz89.hometask.repository.json;

import com.vz89.hometask.dto.DeveloperDTO;
import com.vz89.hometask.model.Developer;
import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.service.JsonService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {
    private static final String DEVELOPERS_JSON = "/files/developers.json";
    private DeveloperMapper developerMapper = new DeveloperMapper();
    private AccountRepository accountRepository = new JsonAccountRepositoryImpl();
    private SkillRepository skillRepository = new JsonSkillRepositoryImpl();

    @Override
    public Developer getById(Long id) {
        return developerMapper.jsonToDeveloper(JsonService.getListFromJson(DEVELOPERS_JSON, DeveloperDTO.class)
                .stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst()
                .get());
    }

    @Override
    public List<Developer> findAll() {
        return JsonService.getListFromJson(DEVELOPERS_JSON, DeveloperDTO.class)
                .stream()
                .map(developerMapper::jsonToDeveloper)
                .collect(Collectors.toList());
    }

    @Override
    public Developer save(Developer developer) {
        List<DeveloperDTO> developers = JsonService.getListFromJson(DEVELOPERS_JSON, DeveloperDTO.class);

        if (developers.isEmpty()) developer.setId(1L);
        else developer.setId(developers.get(developers.size() - 1).getId() + 1);

        developers.add(developerMapper.developerToJson(developer));
        JsonService.writeJsonToFile(developers, DEVELOPERS_JSON);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<DeveloperDTO> developerDTOList = JsonService.getListFromJson(DEVELOPERS_JSON, DeveloperDTO.class)
                .stream()
                .map(developerMapper::jsonToDeveloper)
                .map(s -> s.getId().equals(developer.getId()) ? developer : s)
                .map(developerMapper::developerToJson)
                .collect(Collectors.toList());
        JsonService.writeJsonToFile(developerDTOList, DEVELOPERS_JSON);
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        List<DeveloperDTO> developers = JsonService.getListFromJson(DEVELOPERS_JSON, DeveloperDTO.class);
        developers.remove(developerMapper.developerToJson(getById(id)));
        JsonService.writeJsonToFile(developers, DEVELOPERS_JSON);
    }

    public class DeveloperMapper {

        DeveloperDTO developerToJson(Developer developer) {
            return new DeveloperDTO(
                    developer.getId(),
                    developer.getFirstName(),
                    developer.getLastName(),
                    developer.getSkills().stream()
                            .map(Skill::getId)
                            .collect(Collectors.toList())
                            .toArray(Long[]::new),
                    developer.getAccount().getId()
            );
        }

        Developer jsonToDeveloper(DeveloperDTO developerDTO) {
            return new Developer(
                    developerDTO.getId(),
                    developerDTO.getFirstName(),
                    developerDTO.getLastName(),
                    Arrays.stream(developerDTO.getSkills())
                            .map(s -> skillRepository.getById(s))
                            .collect(Collectors.toSet()),
                    accountRepository.getById(developerDTO.getAccount())
            );
        }
    }
}
