package com.vz89.hometask.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "developer")
@NamedEntityGraph(name = "account_entity_graph", attributeNodes = {@NamedAttributeNode("account"), @NamedAttributeNode("skills")})
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "developer_skill",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Developer() {
    }

    public Developer(Long id, Set<Skill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public Developer(Long id, String firstName, String lastName, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public Developer(Long id, String firstName, String lastName, Set<Skill> skills, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.account = account;
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
        if (id == null) {
            return id == developer.id &&
                    firstName.equals(developer.firstName) &&
                    lastName.equals(developer.lastName) &&
                    account.equals(developer.account);
        } else return id.equals(developer.id) &&
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
