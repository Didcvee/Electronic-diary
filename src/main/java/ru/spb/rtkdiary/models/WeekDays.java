package ru.spb.rtkdiary.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "week")
public class WeekDays {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    private int value;
    @Column(name = "week_day")
    private String week_day;

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public WeekDays(int id, int value, List<Teachers> teachers) {
        this.id = id;
        this.value = value;
        this.teachers = teachers;
    }

    @JoinTable(name = "tea_sub_group",
            joinColumns = @JoinColumn(name = "week_id"),
            inverseJoinColumns = @JoinColumn(name = "tea_id"))
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Teachers> teachers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Teachers> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teachers> teachers) {
        this.teachers = teachers;
    }

    public WeekDays() {

    }
}
