package main.java.com.vz89.hometask.model;

import java.util.Objects;

public class Skill {
    private Long id;
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill() {
    }

    public Skill(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Skill(Skill skill) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
                Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
