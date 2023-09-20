package ru.spb.rtkdiary.Request;

import ru.spb.rtkdiary.models.WeekDays;
import ru.spb.rtkdiary.utils.ListSubjectIdAndGroupId;

import java.util.List;

public class TeacherRequest {
    private int id;
    private List<WeekDays> weekDays;

    public List<WeekDays> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDays> weekDays) {
        this.weekDays = weekDays;
    }

    public TeacherRequest(int id, List<WeekDays> weekDays, String name, List<ListSubjectIdAndGroupId> listSubjectIdAndGroupId) {
        this.id = id;
        this.weekDays = weekDays;
        this.name = name;
        this.listSubjectIdAndGroupId = listSubjectIdAndGroupId;
    }

    public TeacherRequest(int id, String name, List<ListSubjectIdAndGroupId> listSubjectIdAndGroupId) {
        this.id = id;
        this.name = name;
        this.listSubjectIdAndGroupId = listSubjectIdAndGroupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private List<ListSubjectIdAndGroupId> listSubjectIdAndGroupId;

    public List<ListSubjectIdAndGroupId> getListSubjectIdAndGroupId() {
        return listSubjectIdAndGroupId;
    }

    public void setListSubjectIdAndGroupId(List<ListSubjectIdAndGroupId> listSubjectIdAndGroupId) {
        this.listSubjectIdAndGroupId = listSubjectIdAndGroupId;
    }

    @Override
    public String toString() {
        return "TeacherRequest{" +
                "name='" + name + '\'' +
                ", listSubjectIdAndGroupId=" + listSubjectIdAndGroupId +
                '}';
    }

    public TeacherRequest(String name, List<ListSubjectIdAndGroupId> listSubjectIdAndGroupId) {
        this.name = name;
        this.listSubjectIdAndGroupId = listSubjectIdAndGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public TeacherRequest() {
    }
}
