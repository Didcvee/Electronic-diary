package ru.spb.rtkdiary.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DAO.TeacherDAO;
import ru.spb.rtkdiary.DAO.TeachersDTO;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Repo.HibernateRepo.TeacherRepository;
import ru.spb.rtkdiary.Repo.SpringDataJpaRepo.TeacherRepositoryJpa;
import ru.spb.rtkdiary.Repo.SpringDataJpaRepo.WeekRepository;
import ru.spb.rtkdiary.Request.GradeRequest;
import ru.spb.rtkdiary.Request.TeacherRequest;
import ru.spb.rtkdiary.models.*;
import ru.spb.rtkdiary.utils.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherRepositoryJpa teacherRepositoryJpa;
    private final WeekRepository weekRepository;

    private final TeacherDAO teacherDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          TeacherRepositoryJpa teacherRepositoryJpa, WeekRepository weekRepository, TeacherDAO teacherDAO) {
        this.teacherRepository = teacherRepository;
        this.teacherRepositoryJpa = teacherRepositoryJpa;
        this.weekRepository = weekRepository;
        this.teacherDAO = teacherDAO;
    }
    public List<GroupWithSubjects> getPackageWithGroupsAndSubjects(@Param("TeacherId") int teacherId){
        return teacherRepository.getPackageWithGroupsAndSubjects(teacherId);
    }
    public TeachersDTO getTeachersDtoById(int id){
        return teacherRepository.getTeacherDtoById(id);
    }
//    @Transactional
//    public void saveGrade(GradeRequest gradeRequest){
//        Grade grade = new Grade();
//        System.out.println(gradeRequest);
//        Peoples peoples = new Peoples();
//        peoples.setId(gradeRequest.getStudentId());
//        grade.setTeachersId(gradeRequest.getTeachersId());
//        grade.setSubjectsId(gradeRequest.getSubjectId());
//        grade.setDate(gradeRequest.getDate());
//        grade.setGroupId(gradeRequest.getGroupId());
//        grade.setPeoples(peoples);
//        grade.setGrade(gradeRequest.getGrade());
//        entityManager.persist(grade);
//    }

    public ListWithStudentHisGradeAndDatesOfThisTeacher getGradesByTeacherIdSubjectIdAndGroupId(int teacherId, int subjectId, int groupId, int year, int month){
        return teacherRepository.getGradesByTeacherSubjectAndGroup(teacherId,subjectId,groupId,year,month);
    }
    public List<TeachersDTO> findAll(){
//        List<TeachersDTO> teachersDTOList = new ArrayList<>();
//        teacherRepositoryJpa.findAll()
//                .forEach(teachers ->
//                        teachersDTOList.add(new TeachersDTO(teachers.getId(), teachers.getName())));
//        return teachersDTOList;
        List<TeachersDTO> teachersDTOList = new ArrayList<>();
        String sql = "select t.id as id,t.name as name from teachers t";
        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();
        list.forEach(objects -> teachersDTOList.add(new TeachersDTO((Integer)objects[0],(String) objects[1])));
        return teachersDTOList;
    }
    @Transactional
    public void save(TeacherDTOSHKA teacherDTOSHKA) {
        teacherDAO.save(teacherDTOSHKA);
    }
    public Teachers findTeacherByIdWithSubjectsAndGroups(int teacherId){
         return teacherRepository.findByIdWithSubjectsAndGroups(teacherId);
    }
    @Transactional
    public void update(TeacherDTOSHKA teacherDTOSHKA){
        teacherDAO.update(teacherDTOSHKA);
    }
    public List<WeekDays> findAllWeek(){
        return weekRepository.findAll();
    }
    public TeacherDTOSHKA findOne(int teacherId){
        Query query = entityManager.createNativeQuery("SELECT DISTINCT t.id AS teacher_id, t.name AS teacher_name, s.id AS subject_id, s.name AS subject_name, g.id AS group_id, g.name AS group_name, w.id AS week_id, w.week_day AS week_day, w.value AS week_value FROM teachers t JOIN tea_sub_group tsg ON t.id = tsg.tea_id JOIN subjects s ON s.id = tsg.sub_id JOIN group_ g ON g.id = tsg.group_id JOIN week w ON w.id = tsg.week_id WHERE tsg.tea_id=:tea");
        query.setParameter("tea",teacherId);
        TeacherDTOSHKA teacherDTO = new TeacherDTOSHKA();
        List<GroupSubjectTeacherWeek> groupSubjectTeacherWeekList = new ArrayList<>();
        List<Object[]> resultList = query.getResultList();
        for (Object[] result : resultList) {
            teacherDTO.setId((Integer) result[0]);
            teacherDTO.setName((String) result[1]);
            GroupSubjectTeacherWeek groupSubjectTeacherWeek = new GroupSubjectTeacherWeek();
            groupSubjectTeacherWeek.setSubjectId((Integer) result[2]);
            groupSubjectTeacherWeek.setSubjectName((String) result[3]);
            groupSubjectTeacherWeek.setGroupId((Integer) result[4]);
            groupSubjectTeacherWeek.setGroupName((String) result[5]);
            groupSubjectTeacherWeek.setWeekId((Integer) result[6]);
            groupSubjectTeacherWeek.setWeekName((String) result[7]);
            groupSubjectTeacherWeek.setWeekValue((Integer) result[8]);
            groupSubjectTeacherWeekList.add(groupSubjectTeacherWeek);
            System.out.println(groupSubjectTeacherWeek);
        }

        teacherDTO.setGroupSubjectTeacherWeekList(groupSubjectTeacherWeekList);
        return teacherDTO;

    }
    public  List<Integer> findWeekInList(int subId,int teaId,int groupId){
        Query query = entityManager.createNativeQuery("select w.value as value from week w join tea_sub_group tsg on w.id=tsg.week_id where tsg.tea_id=:tea and tsg.sub_id=:sub and tsg.group_id=:gro");
        query.setParameter("tea",teaId);
        query.setParameter("sub",subId);
        query.setParameter("gro",groupId);
        return query.getResultList();


    }


}
