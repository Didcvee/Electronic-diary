package ru.spb.rtkdiary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "grade")
@NamedQuery(name = "Grade.findGradeByTeachersIdAndSubjectsIdAndGroupIdAndDateYearAndDateMonth",
        query = "SELECT g FROM Grade g WHERE g.teachersId = :teachersId AND g.subjectsId = :subjectsId AND g.groupId = :groupId AND YEAR(g.date) = :year AND MONTH(g.date) = :month")
public class Grade {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "teachersid")
    private int teachersId;
    @Column(name = "subjectid")
    private int subjectsId;
    @Column(name = "groupid")
    private int groupId;
    @Column(name = "date")
    private Date date;
    @Column(name = "grade")
    private int grade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid", referencedColumnName = "id")
    private Peoples peoples;
    public int getTeachersId() {
        return teachersId;
    }
    public void setTeachersId(int teachersId) {
        this.teachersId = teachersId;
    }
    public int getSubjectsId() {
        return subjectsId;
    }
    public void setSubjectsId(int subjectsId) {
        this.subjectsId = subjectsId;
    }
    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public Grade(int id, int teachersId, int subjectsId, int groupId, Date date, int grade, Peoples peoples) {
        this.id = id;
        this.teachersId = teachersId;
        this.subjectsId = subjectsId;
        this.groupId = groupId;
        this.date = date;
        this.grade = grade;
        this.peoples = peoples;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Peoples getPeoples() {
        return peoples;
    }
    public void setPeoples(Peoples peoples) {
        this.peoples = peoples;
    }

    public Grade( Date date, int grade) {

        this.date = date;
        this.grade = grade;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public Grade() {
    }
}
