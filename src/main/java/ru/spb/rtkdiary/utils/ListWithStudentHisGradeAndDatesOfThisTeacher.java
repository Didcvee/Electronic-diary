package ru.spb.rtkdiary.utils;

import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Request.GradeRequest;

import java.util.List;

public class ListWithStudentHisGradeAndDatesOfThisTeacher {
    List<GradeRequest> gradeRequestList;
    List<String> dateList;
    List<StudentDTO> studentDTOList;

    public ListWithStudentHisGradeAndDatesOfThisTeacher() {
    }

    public List<GradeRequest> getGradeRequestList() {
        return gradeRequestList;
    }

    public void setGradeRequestList(List<GradeRequest> gradeRequestList) {
        this.gradeRequestList = gradeRequestList;
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

    public ListWithStudentHisGradeAndDatesOfThisTeacher(List<GradeRequest> gradeRequestList, List<String> dateList, List<StudentDTO> studentDTOList) {
        this.gradeRequestList = gradeRequestList;
        this.dateList = dateList;
        this.studentDTOList = studentDTOList;
    }
}
