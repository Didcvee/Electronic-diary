package ru.spb.rtkdiary.Repo.HibernateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.DAO.TeachersDTO;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Repo.SpringDataJpaRepo.GradeRepository;
import ru.spb.rtkdiary.Request.GradeRequest;
import ru.spb.rtkdiary.models.*;
import ru.spb.rtkdiary.utils.Date;
import ru.spb.rtkdiary.utils.GroupWithSubjects;
import ru.spb.rtkdiary.utils.ListWithStudentHisGradeAndDatesOfThisTeacher;
import ru.spb.rtkdiary.utils.WrapperSubjectsDTOAndGroupDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    public List<GroupWithSubjects> getPackageWithGroupsAndSubjects(@Param("Teacher Id") int teacherId){
        String sql = "select distinct g.id as groupId, g.name as groupName, s.name as subjectName, s.id as subjectId " +
                "from teachers t join tea_sub_group tsg on t.id = tsg.tea_id " +
                "join group_ g on g.id = tsg.group_id join subjects s on s.id = tsg.sub_id where t.id=:tea";
        Query query = entityManager.createNativeQuery(sql).setParameter("tea",teacherId);
        List<Object[]> objects = query.getResultList();
        List<GroupWithSubjects> groupWithSubjectsList = new ArrayList<>();
        objects.forEach(objects1 -> groupWithSubjectsList.add(new GroupWithSubjects(
                new Subjects((String) objects1[1],(Integer) objects1[0]),
                        new Group((String) objects1[2],(Integer) objects1[3]))));
        return groupWithSubjectsList;
    }

    public TeachersDTO getTeacherDtoById(int teacherId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeachersDTO> criteriaQuery = criteriaBuilder.createQuery(TeachersDTO.class);
        Root<Teachers> teacherRoot = criteriaQuery.from(Teachers.class);
        // Выбор полей для TeacherDto
        criteriaQuery.select(criteriaBuilder.construct(
                TeachersDTO.class,
                teacherRoot.get("id"),
                teacherRoot.get("name")
        ));
        // Условие для выборки по id
        criteriaQuery.where(criteriaBuilder.equal(teacherRoot.get("id"), teacherId));

        // Выполняем запрос и возвращаем результат
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public ListWithStudentHisGradeAndDatesOfThisTeacher getGradesByTeacherSubjectAndGroup(int teacherId, int subjectId, int groupId, int year, int month) {
        Query query1 = entityManager.createNativeQuery("select w.value as value from week w join tea_sub_group tsg on w.id=tsg.week_id where tsg.tea_id=:tea and tsg.sub_id=:sub and tsg.group_id=:gro");
        query1.setParameter("tea",teacherId);
        query1.setParameter("sub",subjectId);
        query1.setParameter("gro",groupId);
        List<Integer> weekDays = query1.getResultList();
        List<String> dates = Date.getDates(month,year,weekDays);

        List<GradeRequest> gradeRequestList = new ArrayList<>();
        List<Grade> gradeList = gradeRepository.findGradeByTeachersIdAndSubjectsIdAndGroupIdAndDateYearAndDateMonth(teacherId,subjectId,
                groupId,year,month);

        gradeList.forEach(grade -> gradeRequestList.add(new GradeRequest(grade.getId(),grade.getGrade(),grade.getPeoples().getId(),grade.getDate().toString())));
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

    public Teachers findByIdWithSubjectsAndGroups(int teacherId){
        String query = "SELECT distinct t FROM Teachers t JOIN FETCH t.subjects join t.weekDays WHERE t.id = :teacherId";
        Teachers teacher = entityManager.createQuery(query, Teachers.class)
                .setParameter("teacherId", teacherId)
                .getSingleResult();
        return teacher;
    }
}

