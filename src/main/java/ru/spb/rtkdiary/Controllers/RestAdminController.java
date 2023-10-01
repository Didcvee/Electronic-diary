package ru.spb.rtkdiary.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spb.rtkdiary.DTO.*;
import ru.spb.rtkdiary.Services.*;
import ru.spb.rtkdiary.models.WeekDays;
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


    //TODO ---------------------------- GET ALL ---------------------------- GET ALL ----------------------------
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
        return ResponseEntity.ok().body(groupService.findAll()); //
    }
    @GetMapping("/findAllWeeks")
    public ResponseEntity<List<WeekDays>> findAllWeeks(){
        return ResponseEntity.ok().body(teacherService.findAllWeek()); //
    }

    //TODO ---------------------------- GET BY ID ---------------------------- GET BY ID ----------------------------
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> findOneStudent(@PathVariable("id") int id){
        return ResponseEntity.ok().body(studentsService.findById(id)); //
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDTOSHKA> findOneTeacher(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(teacherService.findOne(id)); //
    }
    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectsDTO> findOneSubject(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(subjectService.findById(id)); //
    }
    @GetMapping("/groups/{id}")
    public ResponseEntity<GroupDTO> findOneGroup(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(groupService.findById(id)); //
    }
    // Отдаем все предметы+группы (будет фильтрация на фронте либо здесь)

    @GetMapping("/{teacherId}/{subjectId}/{groupId}/{Year}/{Month}")
    public ResponseEntity<ListWithStudentHisGradeAndDatesOfThisTeacher> k5(@PathVariable(name = "groupId") int groupId,
                                                                           @PathVariable(name = "subjectId") int subjectId,
                                                                           @PathVariable(name = "teacherId") int teacherId,
                                                                           @PathVariable(name = "Year") int year,
                                                                           @PathVariable(name = "Month") int month){
        return ResponseEntity.ok().body(teacherService.getGradesByTeacherIdSubjectIdAndGroupId(teacherId,subjectId,groupId,year,month)); //
    }

    //TODO ---------------------------- POST ---------------------------- POST ----------------------------
    @PostMapping("/addStudent")
    public void addStudent(@RequestBody StudentDTO studentDTO){
        studentsService.save(studentDTO); //
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "КОБИ БРАЯНТ",
//            "group": {
//        "id": 3
//    }
//    }

    @PostMapping("/addSubject")
    public void addSubject(@RequestBody SubjectsDTO subjectsDTO) {
        subjectService.save(subjectsDTO);//
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "ИЗОБРАЗИТЕЛЬНОЕ ИСКУССТВО"
//    }

    @PostMapping("/addGroup")
    public void addGroup(@RequestBody GroupDTO groupDTO) {
        groupService.save(groupDTO); //
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "name": "IV325"
//    }

    @PostMapping("/addTeacher")
    public void addTeacher(@RequestBody TeacherDTOSHKA teacherDTOSHKA){
        teacherService.save(teacherDTOSHKA); //
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

    //TODO ---------------------------- PUT ---------------------------- PUT ----------------------------
    @PutMapping("/updateStudent")
    public void updateStudent(@RequestBody StudentDTO studentDTO){
        studentsService.update(studentDTO); //
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
    public void updateGroup(@RequestBody GroupDTO groupDTO){
        groupService.update(groupDTO); //
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "id":7,
//            "name": "Е5ЕКЕ"
//    }
    @PutMapping("/updateSubject")
    public void updateSubject(@RequestBody SubjectsDTO subjectsDTO){
        subjectService.update(subjectsDTO); //
    }
    // ↓↓↓ Сюда должен прилетать json вида ↓↓↓
//    {
//        "id":8,
//            "name": "ИНФОРМАТИКА"
//    }
    @PutMapping("/updateTeacher")
    public void updateTeacher(@RequestBody TeacherDTOSHKA teacherDTOSHKA){
        teacherService.update(teacherDTOSHKA); //
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
        gradeService.updateSave(gradeDTOList); //
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
    //TODO ---------------------------- DELETE ---------------------------- DELETE ----------------------------
    //TODO ----- Каскадирование на стороне базы данных -----
    @DeleteMapping("/deleteSubject")
    public void deleteSubject(@RequestBody SubjectsDTO subjectsDTO){
        subjectService.deleteSubject(subjectsDTO);
    }
    @DeleteMapping("/deleteGroup")
    public void deleteGroup(@RequestBody GroupDTO groupDTO){
        groupService.deleteGroup(groupDTO);
    }
    @DeleteMapping("/deleteTeacher")
    public void deleteTeacher(@RequestBody TeachersDTO teachersDTO){
        teacherService.deleteTeacher(teachersDTO);
    }
    @DeleteMapping("/deleteStudent")
    public void deleteStudent(@RequestBody StudentDTO studentDTO){
        studentsService.deleteStudent(studentDTO);
    }

}
