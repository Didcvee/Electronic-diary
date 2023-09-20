package ru.spb.rtkdiary.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spb.rtkdiary.DAO.TeachersDTO;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.Request.GradeRequest;
import ru.spb.rtkdiary.Services.TeacherService;
import ru.spb.rtkdiary.models.Teachers;
import ru.spb.rtkdiary.utils.GroupWithSubjects;
import ru.spb.rtkdiary.utils.TeacherDTOSHKA;
import ru.spb.rtkdiary.utils.WrapperSubjectsDTOAndGroupDTO;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {
    private final TeacherService teacherService;
    @Autowired
    public RestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @GetMapping("/test1")
    public ResponseEntity<List<GroupWithSubjects>> k3(){
        return ResponseEntity.ok().body(teacherService.getPackageWithGroupsAndSubjects(1));
    }
    @GetMapping("/test2")
    public ResponseEntity<TeachersDTO> k4(){
        return ResponseEntity.ok().body(teacherService.getTeachersDtoById(1));
    }

//    @PostMapping("/save")
//    public void saveGrade(@RequestBody GradeRequest gradeRequest) {
//        teacherService.saveGrade(gradeRequest);
//    }

    @GetMapping("/testt")
    public TeacherDTOSHKA sss(){
        return teacherService.findOne(1);
    }

    @GetMapping("/testtt")
    public List<Integer> ass(){
        return teacherService.findWeekInList(1,1,1);
    }



}
