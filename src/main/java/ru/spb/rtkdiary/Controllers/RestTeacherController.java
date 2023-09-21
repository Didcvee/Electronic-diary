package ru.spb.rtkdiary.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.Services.GradeService;
import ru.spb.rtkdiary.Services.TeacherService;
import ru.spb.rtkdiary.utils.GroupWithSubjects;
import ru.spb.rtkdiary.utils.ListWithStudentHisGradeAndDatesOfThisTeacher;
import ru.spb.rtkdiary.utils.TeacherDTOSHKA;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class RestTeacherController {
    private final TeacherService teacherService;
    private final GradeService gradeService;

    @Autowired
    public RestTeacherController(TeacherService teacherService, GradeService gradeService) {
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }
    // Получить учителя по айди
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTOSHKA> findOneTeacher(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(teacherService.findOne(id)); //
    }
    // Отдаем все предметы+группы (будет фильтрация на фронте либо здесь)
    @GetMapping("/{id}/find")
    public ResponseEntity<List<GroupWithSubjects>> k3(@PathVariable("id") int id){
        return ResponseEntity.ok().body(teacherService.getPackageWithGroupsAndSubjects(id)); //
    }
    // Получение оценок по уникальности (учитель+группа+предмет+день недели (потому что учитель может вести разные группы+предметы в разные дни) ),
    // также либо здесь, либо на фронте будет делаться запрос по текущему месяцу и текущему году, также будет фильтрация по месяцу,
    // к примеру, сейчас этот контроллер вернет оценки одного месяца, одного года, но также у учителя будет кнопка (смена месяца) вправо влево и
    // и при каждом нажатии на кнопку будет делаться запрос сюда с другим месяцем и с другим годом, если это с декабря на январь к примеру
    @GetMapping("/{teacherId}/find/{subjectId}/{groupId}/{Year}/{Month}")
    public ResponseEntity<ListWithStudentHisGradeAndDatesOfThisTeacher> k5(@PathVariable(name = "groupId") int groupId,
                                                                           @PathVariable(name = "subjectId") int subjectId,
                                                                           @PathVariable(name = "teacherId") int teacherId,
                                                                           @PathVariable(name = "Year") int year,
                                                                           @PathVariable(name = "Month") int month){
        return ResponseEntity.ok().body(teacherService.getGradesByTeacherIdSubjectIdAndGroupId(teacherId,subjectId,groupId,year,month)); //
    }
    // Обновление пришедщих оценок
    @PutMapping("/updateGrade")
    public void updateGrade(@RequestBody List<GradeDTO> gradeDTOList){
        gradeService.updateSave(gradeDTOList);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    [
//    {
//        "id": "",
//            "grade": 5,
//            "peoplesId": 1,
//            "groupId": 1,
//            "teachersId": 1,
//            "subjectsId": 1,
//            "date": "2023-09-18"
//    },
//    {
//        "id": "",
//            "grade": 5,
//            "peoplesId": 1,
//            "groupId": 1,
//            "teachersId": 1,
//            "subjectsId": 1,
//            "date": "01-09-2023"
//    }
//    ]

}
