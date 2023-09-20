package ru.spb.rtkdiary.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subjects")
public class Subjects {
    @Column(name = "name")
    private String name;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tea_sub_group",
    joinColumns = @JoinColumn(name = "sub_id"),
    inverseJoinColumns = @JoinColumn(name = "tea_id"))
    @JsonIgnore
    private List<Teachers> teachers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tea_sub_group",
            joinColumns = @JoinColumn(name = "sub_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @JsonIgnore
    private List<Group> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Teachers> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teachers> teachers) {
        this.teachers = teachers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subjects subjects = (Subjects) o;
        return id == subjects.id && Objects.equals(name, subjects.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "Subjects{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Subjects(String name, int id, List<Teachers> teachers, List<Group> groups) {
        this.name = name;
        this.id = id;
        this.teachers = teachers;
        this.groups = groups;
    }

    public Subjects(String name, int id) {
        this.name = name;
        this.id = id;
    }



    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }



    public Subjects() {
    }

    public Subjects(String name) {
        this.name = name;
    }
}
