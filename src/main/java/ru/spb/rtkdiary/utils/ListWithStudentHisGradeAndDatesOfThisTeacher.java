package ru.spb.rtkdiary.utils;

import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.DTO.StudentDTO;

import java.util.List;

public class ListWithStudentHisGradeAndDatesOfThisTeacher {
    List<GradeDTO> gradeDTOList;
    List<String> dateList;

    List<StudentDTO> studentDTOList;

    public ListWithStudentHisGradeAndDatesOfThisTeacher() {
    }



    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<StudentDTO> getStudentDTOList() {
        return studentDTOList;
    }

    public void setStudentDTOList(List<StudentDTO> studentDTOList) {
        this.studentDTOList = studentDTOList;
    }

    public ListWithStudentHisGradeAndDatesOfThisTeacher(List<GradeDTO> gradeDTOList, List<String> dateList, List<StudentDTO> studentDTOList) {
        this.gradeDTOList = gradeDTOList;
        this.dateList = dateList;
        this.studentDTOList = studentDTOList;
    }
}
