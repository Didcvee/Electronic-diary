package ru.spb.rtkdiary.utils;

public class ListSubjectIdAndGroupId {
    private int subjectId;
    private int groupId;

    @Override
    public String toString() {
        return "ListSubjectIdAndGroupId{" +
                "subjectId=" + subjectId +
                ", groupId=" + groupId +
                '}';
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public ListSubjectIdAndGroupId(int subjectId, int groupId) {
        this.subjectId = subjectId;
        this.groupId = groupId;
    }

    public ListSubjectIdAndGroupId() {
    }
}
