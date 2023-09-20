package ru.spb.rtkdiary.utils;

import ru.spb.rtkdiary.DTO.GradeDTO;

import java.util.List;

public class StudentDateWithGrades {
    private List<String> dates;
    private List<GradeDTO> gradeDTOList;

    public StudentDateWithGrades() {
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<GradeDTO> getGradeDTOList() {
        return gradeDTOList;
    }

    public void setGradeDTOList(List<GradeDTO> gradeDTOList) {
        this.gradeDTOList = gradeDTOList;
    }

    public StudentDateWithGrades(List<String> dates, List<GradeDTO> gradeDTOList) {
        this.dates = dates;
        this.gradeDTOList = gradeDTOList;
    }
}
