package ru.spb.rtkdiary.models;

import com.fasterxml.jackson.annotation.*;
import ru.spb.rtkdiary.utils.GroupWithSubjects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_")
public class Group{

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "tea_sub_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "tea_id"))
    @JsonIgnore
    private List<Teachers> teachers;

    public Group(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Subjects> subjects;

    public List<Peoples> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<Peoples> peoples) {
        this.peoples = peoples;
    }

    public Group(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Peoples> peoples;

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

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public Group() {
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
