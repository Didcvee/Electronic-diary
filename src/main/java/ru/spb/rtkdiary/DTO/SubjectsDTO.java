package ru.spb.rtkdiary.DTO;

public class SubjectsDTO {
    private int id;
    private String name;

    public SubjectsDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public SubjectsDTO() {
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
