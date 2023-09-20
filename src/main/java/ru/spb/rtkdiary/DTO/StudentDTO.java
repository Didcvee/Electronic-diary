package ru.spb.rtkdiary.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.spb.rtkdiary.models.Group;

import java.util.List;

public class StudentDTO {
    private int id;
    private String name;

    public StudentDTO() {
    }

    public StudentDTO(int id, String name, Group group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public StudentDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
