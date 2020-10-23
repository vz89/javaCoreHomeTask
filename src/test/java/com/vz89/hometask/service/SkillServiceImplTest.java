package com.vz89.hometask.service;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SkillServiceImplTest {

    @Mock
    SkillRepository skillRepository;

    @Test
    void shouldCorrectGetById() {
        SkillService skillService = new SkillServiceImpl(skillRepository);
        Skill expectedAccount = new Skill(1L, "php");
        Mockito.when(skillRepository.getById(1L)).thenReturn(new Skill(1L, "php"));
        assertEquals(expectedAccount, skillService.getById(1L));
    }

    @Test
    void shouldFindAll() {
        SkillService skillService = new SkillServiceImpl(skillRepository);
        List<Skill> skillList = new ArrayList<>() {{
            new Skill(1L, "php");
            new Skill(2L, "html");
        }};
        Mockito.when(skillRepository.findAll()).thenReturn(new ArrayList<>() {{
            new Skill(1L, "php");
            new Skill(2L, "html");
        }});
        assertEquals(skillList, skillService.findAll());
    }

    @Test
    void shouldCorrectSave() {
        SkillService skillService = new SkillServiceImpl(skillRepository);
        Skill expectedAccount = new Skill(1L, "php");
        Mockito.when(skillRepository.save(new Skill(null, "php"))).thenReturn(new Skill(1L, "php"));
        assertEquals(expectedAccount, skillService.save("php"));
    }

    @Test
    void shouldCorrectUpdate() {
        SkillService skillService = new SkillServiceImpl(skillRepository);
        Skill expectedAccount = new Skill(1L, "php");
        Mockito.when(skillRepository.update(new Skill(1L, "php"))).thenReturn(new Skill(1L, "php"));
        assertEquals(expectedAccount, skillService.update(1L, "php"));
    }

    @Test
    void shouldCorrectDelete() {
        SkillServiceImpl skillService = new SkillServiceImpl(skillRepository);
    }
}