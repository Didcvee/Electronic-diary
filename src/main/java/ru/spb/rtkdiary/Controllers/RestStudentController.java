package ru.spb.rtkdiary.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Services.GradeService;
import ru.spb.rtkdiary.Services.StudentsService;
import ru.spb.rtkdiary.utils.StudentDateWithGrades;

@RestController
@RequestMapping("/student")
public class RestStudentController {
    private final StudentsService studentsService;
    private final GradeService gradeService;

    @Autowired
    public RestStudentController(StudentsService studentsService, GradeService gradeService) {
        this.studentsService = studentsService;
        this.gradeService = gradeService;
    }
    // Получение ученика по айди
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findOneStudent(@PathVariable("id") int id){
        return ResponseEntity.ok().body(studentsService.findById(id));
    }
    // Страница для просмотра оценок
    @GetMapping("/{id}/{month}/{year}")
    public ResponseEntity<StudentDateWithGrades> findAllDateByMonthAndGradesInThisMonthAndYear(@PathVariable("id") int id,
                                                                                               @PathVariable("month") int month,
                                                                                               @PathVariable("year") int year){
        return ResponseEntity.ok().body(gradeService.findForStudent(id, month, year)); //
    }

}
