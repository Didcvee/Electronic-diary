package ru.spb.rtkdiary.DTO;

import ru.spb.rtkdiary.models.Peoples;

import java.util.List;

public class GroupDTO {
    private int id;
    private String name;
    private List<Peoples> peoples;

    public GroupDTO(int id, String name, List<Peoples> peoples) {
        this.id = id;
        this.name = name;
        this.peoples = peoples;
    }

    public List<Peoples> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<Peoples> peoples) {
        this.peoples = peoples;
    }

    public GroupDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public GroupDTO() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
