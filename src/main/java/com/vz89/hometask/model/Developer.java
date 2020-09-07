package main.java.com.vz89.hometask.model;

import java.util.Set;

public class Developer {
    private Long id;
    private Set<Skill> skills;
    private Account account;

    public Developer(Long id, Set<Skill> skills, Account account) {
        this.id = id;
        this.skills = skills;
        this.account = account;
    }

    public Developer(Set<Skill> skills) {
        this.skills = skills;
    }

    public Developer() {
    }

    public Developer(Long id, Set<Skill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
