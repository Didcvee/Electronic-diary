package ru.spb.rtkdiary.utils;

public class GroupSubjectTeacherWeek {
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "GroupSubjectTeacherWeek{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", weekId=" + weekId +
                ", weekName='" + weekName + '\'' +
                ", weekValue=" + weekValue +
                '}';
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getWeekId() {
        return weekId;
    }

    public void setWeekId(int weekId) {
        this.weekId = weekId;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public int getWeekValue() {
        return weekValue;
    }

    public void setWeekValue(int weekValue) {
        this.weekValue = weekValue;
    }

    public GroupSubjectTeacherWeek() {
    }

    private int subjectId;
    private String subjectName;
    private int groupId;
    private String groupName;
    private int weekId;
    private String weekName;
    private int weekValue;
}
