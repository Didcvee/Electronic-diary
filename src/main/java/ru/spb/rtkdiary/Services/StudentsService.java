package ru.spb.rtkdiary.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Exception.UserNotFoundException;
import ru.spb.rtkdiary.Repo.StudentRepository;
import ru.spb.rtkdiary.Repo.StudentsRepository;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Peoples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentsService {
    private final StudentsRepository studentsRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository, StudentRepository studentRepository) {
        this.studentsRepository = studentsRepository;
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> findAll(){
        try {
            return studentRepository.findAll(); //
        } catch (UserNotFoundException ex){
            throw ex;
        }
    }

    public StudentDTO findById(int id){
        Peoples peoples = studentsRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Студент не найдены"));
        return new StudentDTO(peoples.getId(), peoples.getName(),peoples.getGroup());

    }

    @Transactional
    public void save(StudentDTO studentDTO){
        Peoples peoples = new Peoples(studentDTO.getName(),studentDTO.getGroup());
        studentsRepository.save(peoples); //
    }

    @Transactional
    public void update(StudentDTO studentDTO){
        Peoples peoples = new Peoples(studentDTO.getId(),studentDTO.getName(),studentDTO.getGroup());
        studentsRepository.save(peoples); //
    }
    @Transactional
    public void deleteStudent(StudentDTO studentDTO){
        entityManager.createNativeQuery("delete from student where id=:id").setParameter("id",studentDTO.getId()).executeUpdate();
        //
    }
}
