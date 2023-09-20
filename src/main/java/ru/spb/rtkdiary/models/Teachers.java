package ru.spb.rtkdiary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teachers {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public List<WeekDays> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDays> weekDays) {
        this.weekDays = weekDays;
    }

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<WeekDays> weekDays;

    public Teachers(int id, String name, List<Subjects> subjects, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.groups = groups;
    }

    public Teachers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    private List<Subjects> subjects;

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    private List<Group> groups;

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

    public Teachers(String name) {
        this.name = name;
    }

    public Teachers() {
    }
}
