package ru.spb.rtkdiary.Request;

public class GroupRequest {
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupRequest(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public GroupRequest() {
    }

    public GroupRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
