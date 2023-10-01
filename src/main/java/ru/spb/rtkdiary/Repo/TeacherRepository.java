package ru.spb.rtkdiary.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.DTO.TeachersDTO;
import ru.spb.rtkdiary.Exception.UserNotFoundException;
import ru.spb.rtkdiary.models.Grade;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Peoples;
import ru.spb.rtkdiary.models.Subjects;
import ru.spb.rtkdiary.utils.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final GradeRepository gradeRepository;
    @Autowired
    public TeacherRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public ListWithStudentHisGradeAndDatesOfThisTeacher getGradesByTeacherSubjectAndGroup(int teacherId, int subjectId, int groupId, int year, int month) {
        Query query1 = entityManager.createNativeQuery("select w.value as value from week w join tea_sub_group tsg on w.id=tsg.week_id where tsg.tea_id=:tea and tsg.sub_id=:sub and tsg.group_id=:gro");
        query1.setParameter("tea",teacherId);
        query1.setParameter("sub",subjectId);
        query1.setParameter("gro",groupId);
        List<Integer> weekDays = query1.getResultList();
        List<String> dates = Date.getDates(month,year,weekDays);

        List<GradeDTO> gradeRequestList = new ArrayList<>();
        List<Grade> gradeList = gradeRepository.findGradeByTeachersIdAndSubjectsIdAndGroupIdAndDateYearAndDateMonth(teacherId,subjectId,
                groupId,year,month);

        gradeList.forEach(grade -> gradeRequestList.add(new GradeDTO(grade.getId(),grade.getGrade(),grade.getPeoples().getId(),grade.getDate().toString())));
        CriteriaBuilder criteriaBuilder1 = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentDTO> criteriaQuery1 = criteriaBuilder1.createQuery(StudentDTO.class);
        Root<Peoples> root1 = criteriaQuery1.from(Peoples.class);
        criteriaQuery1.select(criteriaBuilder1.construct(
                StudentDTO.class,
                root1.get("id"),
                root1.get("name"),
                root1.get("group")
        ));
        Predicate predicateGroup1 = criteriaBuilder1.equal(root1.get("group").get("id"), groupId);
        criteriaQuery1.where(predicateGroup1);
        List<StudentDTO> studentDTOList = entityManager.createQuery(criteriaQuery1).getResultList();
        return new ListWithStudentHisGradeAndDatesOfThisTeacher(gradeRequestList,dates,studentDTOList);
    }
    public TeacherDTOSHKA findOne(int teacherId) throws UserNotFoundException{
        Query query = entityManager.createNativeQuery("SELECT DISTINCT t.id AS teacher_id, t.name AS teacher_name, s.id AS subject_id, s.name AS subject_name,\n" +
                "                g.id AS group_id, g.name AS group_name, w.id AS week_id, w.week_day AS week_day,\n" +
                "                w.value AS week_value FROM teachers t left JOIN tea_sub_group tsg ON t.id = tsg.tea_id\n" +
                "                     left JOIN subjects s ON s.id = tsg.sub_id left JOIN group_ g ON g.id = tsg.group_id\n" +
                "                    left JOIN week w ON w.id = tsg.week_id WHERE t.id=:tea");
        query.setParameter("tea",teacherId);
        TeacherDTOSHKA teacherDTO = new TeacherDTOSHKA();
        List<GroupSubjectTeacherWeek> groupSubjectTeacherWeekList = new ArrayList<>();
        List<Object[]> resultList = query.getResultList();
        if(resultList.isEmpty()) throw new UserNotFoundException("Учитель не найден");
        teacherDTO.setName((String) resultList.get(0)[1]);
        teacherDTO.setId((Integer) resultList.get(0)[0]);
        if(resultList.get(0)[2]!=null){
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
            teacherDTO.setGroupSubjectTeacherWeekList(groupSubjectTeacherWeekList);}
        return teacherDTO; //

    }
    public List<TeachersDTO> findAll() throws UserNotFoundException{
        List<TeachersDTO> teachersDTOList = new ArrayList<>();
        String sql = "select t.id as id,t.name as name from teachers t";
        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();
        if(list.isEmpty()) throw new UserNotFoundException("Ничего не найдено");
        list.forEach(objects -> teachersDTOList.add(new TeachersDTO((Integer)objects[0],(String) objects[1])));
        return teachersDTOList; //
    }


}

