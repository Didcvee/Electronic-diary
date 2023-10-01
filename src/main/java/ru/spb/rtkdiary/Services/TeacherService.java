package ru.spb.rtkdiary.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DAO.TeacherDAO;

import ru.spb.rtkdiary.DTO.TeachersDTO;
import ru.spb.rtkdiary.Exception.UserNotFoundException;
import ru.spb.rtkdiary.Repo.TeacherRepository;
import ru.spb.rtkdiary.Repo.WeekRepository;
import ru.spb.rtkdiary.models.*;
import ru.spb.rtkdiary.utils.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final WeekRepository weekRepository;

    private final TeacherDAO teacherDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          WeekRepository weekRepository, TeacherDAO teacherDAO) {
        this.teacherRepository = teacherRepository;
        this.weekRepository = weekRepository;
        this.teacherDAO = teacherDAO;
    }


    public ListWithStudentHisGradeAndDatesOfThisTeacher getGradesByTeacherIdSubjectIdAndGroupId(int teacherId, int subjectId, int groupId, int year, int month){
        return teacherRepository.getGradesByTeacherSubjectAndGroup(teacherId,subjectId,groupId,year,month); //
    }
    public List<TeachersDTO> findAll(){
        try {
            return teacherRepository.findAll();
        }catch (UserNotFoundException ex){
            throw ex;
        }
    }
    @Transactional
    public void save(TeacherDTOSHKA teacherDTOSHKA) {
        teacherDAO.save(teacherDTOSHKA); //
    }
    @Transactional
    public void update(TeacherDTOSHKA teacherDTOSHKA){
        teacherDAO.update(teacherDTOSHKA); //
    }
    public List<WeekDays> findAllWeek(){
        return weekRepository.findAll(); //
    }
    public TeacherDTOSHKA findOne(int teacherId){
        try {
            return teacherRepository.findOne(teacherId);
        }catch (UserNotFoundException ex){
            throw ex;
        }
    }
    @Transactional
    public void deleteTeacher(TeachersDTO teachersDTO){ //
        entityManager.createNativeQuery("delete from teachers where id=:id").setParameter("id",teachersDTO.getId()).executeUpdate();
    }


}

