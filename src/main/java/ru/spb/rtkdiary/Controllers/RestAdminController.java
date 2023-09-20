package ru.spb.rtkdiary.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.rtkdiary.DAO.TeachersDTO;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.DTO.GroupDTO;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.DTO.SubjectsDTO;
import ru.spb.rtkdiary.Request.GroupRequest;
import ru.spb.rtkdiary.Request.SubjectRequest;
import ru.spb.rtkdiary.Services.*;
import ru.spb.rtkdiary.models.WeekDays;
import ru.spb.rtkdiary.utils.GroupWithSubjects;
import ru.spb.rtkdiary.utils.ListWithStudentHisGradeAndDatesOfThisTeacher;
import ru.spb.rtkdiary.utils.TeacherDTOSHKA;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestAdminController {
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final GroupService groupService;
    private final GradeService gradeService;
    private final StudentsService studentsService;


    @Autowired
    public RestAdminController(TeacherService teacherService, SubjectService subjectService, GroupService groupService, GradeService gradeService, StudentsService studentsService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.groupService = groupService;
        this.gradeService = gradeService;
        this.studentsService = studentsService;
    }

    //TODO---------------------------- GET ALL ---------------------------- GET ALL ----------------------------
    @GetMapping("/findAllStudents")
    public ResponseEntity<List<StudentDTO>> findAllStudents(){
        return ResponseEntity.ok().body(studentsService.findAll());
    }

    @GetMapping("/findAllTeachers")
    public ResponseEntity<List<TeachersDTO>> findAllTeachers() {
        return ResponseEntity.ok().body(teacherService.findAll());
    }
    @GetMapping("/findAllSubjects")
    public ResponseEntity<List<SubjectsDTO>> findAllSubjects() {
        return ResponseEntity.ok().body(subjectService.findAll());
    }
    @GetMapping("/findAllGroups")
    public ResponseEntity<List<GroupDTO>> findAllGroups() {
        return ResponseEntity.ok().body(groupService.findAll());
    }
    @GetMapping("/findAllWeeks")
    public ResponseEntity<List<WeekDays>> findAllWeeks(){
        return ResponseEntity.ok().body(teacherService.findAllWeek());
    }

    //TODO---------------------------- GET BY ID ---------------------------- GET BY ID ----------------------------
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> findOneStudent(@PathVariable("id") int id){
        return ResponseEntity.ok().body(studentsService.findById(id));
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTOSHKA> findOneTeacher(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(teacherService.findOne(id));
    }
    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectsDTO> findOneSubject(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(subjectService.findById(id));
    }
    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupDTO> findOneGroup(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(groupService.findById(id));
    }
    // Отдаем все предметы+группы (будет фильтрация на фронте либо здесь)
    @GetMapping("/{id}/find")
    public ResponseEntity<List<GroupWithSubjects>> k3(@PathVariable("id") int id){
        return ResponseEntity.ok().body(teacherService.getPackageWithGroupsAndSubjects(id));
    }
    @GetMapping("/{teacherId}/{subjectId}/{groupId}/{Year}/{Month}")
    public ResponseEntity<ListWithStudentHisGradeAndDatesOfThisTeacher> k5(@PathVariable(name = "groupId") int groupId,
                                                                           @PathVariable(name = "subjectId") int subjectId,
                                                                           @PathVariable(name = "teacherId") int teacherId,
                                                                           @PathVariable(name = "Year") int year,
                                                                           @PathVariable(name = "Month") int month){
        return ResponseEntity.ok().body(teacherService.getGradesByTeacherIdSubjectIdAndGroupId(teacherId,subjectId,groupId,year,month));
    }

    //TODO---------------------------- POST ---------------------------- POST ----------------------------
    @PostMapping("/addStudent")
    public void addStudent(@RequestBody StudentDTO studentDTO){studentsService.save(studentDTO);}
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "КОБИ БРАЯНТ",
//            "group": {
//        "id": 3
//    }
//    }

    @PostMapping("/addSubject")
    public void addSubject(@RequestBody SubjectRequest subjectRequest) {
        subjectService.save(subjectRequest);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "ИЗОБРАЗИТЕЛЬНОЕ ИСКУССТВО"
//    }

    @PostMapping("/addGroup")
    public void addGroup(@RequestBody GroupRequest groupRequest) {
        groupService.save(groupRequest);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "IV325"
//    }

    @PostMapping("/addTeacher")
    public void addTeacher(@RequestBody TeacherDTOSHKA teacherDTOSHKA){
        teacherService.save(teacherDTOSHKA);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "ВАЛЕНТИНА ИВАНОВНА",
//            "groupSubjectTeacherWeekList":[{
//        "subjectId":2,
//                "groupId":2,
//                "weekId":2
//    }]
//    }

    //TODO---------------------------- PUT ---------------------------- PUT ----------------------------
    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody StudentDTO studentDTO){
        studentsService.update(studentDTO);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "id":12,
//            "name": "КОБИ БРАЯНТ",
//            "group": {
//        "id": 2
//    }
//    }

    @PutMapping("/updateGroup")
    public void updateGroup(@RequestBody GroupRequest groupRequest){
        groupService.update(groupRequest);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "id":7,
//            "name": "Е5ЕКЕ"
//    }
    @PutMapping("/updateSubject")
    public void updateSubject(@RequestBody SubjectRequest subjectRequest){
        subjectService.update(subjectRequest);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "id":8,
//            "name": "ИНФОРМАТИКА"
//    }
    @PutMapping("/updateTeacher")
    public void updateTeacher(@RequestBody TeacherDTOSHKA teacherDTOSHKA){
        teacherService.update(teacherDTOSHKA);
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "id":22,
//            "name": "ЛЮДМИЛА АЛЕКСАНДРОВНА",
//            "groupSubjectTeacherWeekList":[{
//        "subjectId":2,
//                "groupId":2,
//                "weekId":2
//    }]
//    }

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
