package com.vz89.hometask.model;

import java.util.Objects;
import java.util.Set;

public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<Skill> skills;
    private Account account;

    public Developer() {
    }

    public Developer(Long id, Set<Skill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return id.equals(developer.id) &&
                firstName.equals(developer.firstName) &&
                lastName.equals(developer.lastName) &&
                account.equals(developer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, account);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", skills=" + skills +
                ", account=" + account +
                '}';
    }
}
