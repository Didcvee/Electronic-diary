package ru.spb.rtkdiary.utils;

import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Subjects;

import java.util.List;

public class GroupWithSubjects {
    private Subjects subjects;
    private Group group;


    public GroupWithSubjects() {
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupWithSubjects(Subjects subjects, Group group) {
        this.subjects = subjects;
        this.group = group;
    }
}
