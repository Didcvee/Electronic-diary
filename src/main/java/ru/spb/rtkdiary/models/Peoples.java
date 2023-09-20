package ru.spb.rtkdiary.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "people")
public class Peoples {
    public Peoples() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public Peoples(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Peoples(int id, String name, Group group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    @OneToMany(mappedBy = "peoples", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Grade> grades;

    @Override
    public String toString() {
        return "Peoples{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Peoples(String name) {
        this.name = name;
    }

    public Peoples(String name, Group group) {
        this.name = name;
        this.group = group;
    }
}
