package ru.spb.rtkdiary.utils;

import java.util.List;

public class TeacherDTOSHKA {

    private int id;
    private String name;
    private List<GroupSubjectTeacherWeek> groupSubjectTeacherWeekList;

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

    public List<GroupSubjectTeacherWeek> getGroupSubjectTeacherWeekList() {
        return groupSubjectTeacherWeekList;
    }

    public void setGroupSubjectTeacherWeekList(List<GroupSubjectTeacherWeek> groupSubjectTeacherWeekList) {
        this.groupSubjectTeacherWeekList = groupSubjectTeacherWeekList;
    }

    public TeacherDTOSHKA() {
    }
}
