package ru.spb.rtkdiary.DAO;

import org.springframework.stereotype.Component;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Subjects;
import ru.spb.rtkdiary.models.WeekDays;

import java.util.List;


public class TeachersDTO {
    private int id;
    private String name;
    private List<Subjects> subjects;
    private List<Group> groups;
    private List<WeekDays> weekDays;

    public TeachersDTO(int id, String name, List<Subjects> subjects, List<Group> groups, List<WeekDays> weekDays) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.groups = groups;
        this.weekDays = weekDays;
    }

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

    public List<WeekDays> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDays> weekDays) {
        this.weekDays = weekDays;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public TeachersDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeachersDTO() {
    }

    public void setName(String name) {
        this.name = name;
    }
}
