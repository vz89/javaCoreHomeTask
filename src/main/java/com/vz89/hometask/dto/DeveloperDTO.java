package com.vz89.hometask.dto;

import java.util.Arrays;

public class DeveloperDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long[] skills;
    private Long accountId;

    public DeveloperDTO(Long id, String firstName, String lastName, Long[] skills, Long account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.accountId = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long[] getSkills() {
        return skills;
    }

    public Long getAccount() {
        return accountId;
    }

    public void setAccount(Long account) {
        this.accountId = account;
    }
}
