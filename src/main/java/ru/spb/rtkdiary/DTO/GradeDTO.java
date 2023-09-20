package ru.spb.rtkdiary.DTO;

import ru.spb.rtkdiary.models.Peoples;

import java.util.List;
import java.util.StringTokenizer;

public class GradeDTO {
    private int id;
    private int grade;
    private int peoplesId;
    public int getPeoplesId() {
        return peoplesId;
    }

    public void setPeoplesId(int peoplesId) {
        this.peoplesId = peoplesId;
    }
    public String subjectName;

    public GradeDTO(int id, int grade, String subjectName, int subjectsId, String date) {
        this.id = id;
        this.grade = grade;
        this.subjectName = subjectName;
        this.subjectsId = subjectsId;
        this.date = date;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    private int groupId;
    private int teachersId;
    private int subjectsId;
    private String date;

    public GradeDTO() {
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

    @Override
    public String toString() {
        return "GradeDTO{" +
                "id=" + id +
                ", grade=" + grade +
                ", peoplesId=" + peoplesId +
                ", groupId=" + groupId +
                ", teachersId=" + teachersId +
                ", subjectsId=" + subjectsId +
                ", date='" + date + '\'' +
                ", datesOfThisTeacher=" + datesOfThisTeacher +
                '}';
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(int teachersId) {
        this.teachersId = teachersId;
    }

    public GradeDTO(int id, int grade, int groupId, int teachersId, int subjectsId, String date, int peoplesId) {
        this.id = id;
        this.grade = grade;
        this.groupId = groupId;
        this.teachersId = teachersId;
        this.subjectsId = subjectsId;
        this.date = date;
        this.peoplesId=peoplesId;

    }

    public int getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(int subjectsId) {
        this.subjectsId = subjectsId;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> datesOfThisTeacher;

    public List<String> getDatesOfThisTeacher() {
        return datesOfThisTeacher;
    }

    public void setDatesOfThisTeacher(List<String> datesOfThisTeacher) {
        this.datesOfThisTeacher = datesOfThisTeacher;
    }
}
