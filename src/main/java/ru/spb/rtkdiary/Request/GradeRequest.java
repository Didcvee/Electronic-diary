package ru.spb.rtkdiary.Request;

import java.util.List;

public class GradeRequest {
    private int id;
    private int grade;
    private int peopleId;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GradeRequest(int id, int grade, int peopleId, String date) {
        this.id = id;
        this.grade = grade;
        this.peopleId = peopleId;
        this.date = date;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }



    public GradeRequest() {
    }


}
