package ru.spb.rtkdiary.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DTO.GradeDTO;
import ru.spb.rtkdiary.models.Grade;
import ru.spb.rtkdiary.models.Peoples;
import ru.spb.rtkdiary.utils.AllDate;
import ru.spb.rtkdiary.utils.StudentDateWithGrades;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GradeService {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void updateSave(List<GradeDTO> gradeDTO1){
        gradeDTO1.forEach(gradeDTO -> {
            if (gradeDTO.getId()!=0){
                if(gradeDTO.getGrade()==0){
                Grade grade = entityManager.find(Grade.class,gradeDTO.getId());
                entityManager.remove(grade);
            }
            else {
                Grade grade = entityManager.getReference(Grade.class,gradeDTO.getId());
                grade.setGrade(gradeDTO.getGrade());
            }
        }
        else{
            if(gradeDTO.getGrade()!=0){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Peoples peoples = entityManager.find(Peoples.class,gradeDTO.getPeoplesId());
                try {
                    entityManager.persist(new Grade(gradeDTO.getId(), gradeDTO.getTeachersId(), gradeDTO.getSubjectsId(),
                            gradeDTO.getGroupId(), new Date(dateFormat.parse(gradeDTO.getDate()).getTime()), gradeDTO.getGrade(), peoples));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }});
    }
    public StudentDateWithGrades findForStudent(int id, int month, int year){
        String sql= "select g.id,g.grade,g.date,s.name from grade g join subjects s on g.subjectid=s.id where extract(month from date)=:month" +
                " and extract(year from date)=:year and studentId=:stu";
        Query query = entityManager.createNativeQuery(sql).setParameter("stu",id).setParameter("month",month).setParameter("year",year);
        List<Object[]> objects = query.getResultList();
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        objects.forEach(objects1 -> gradeDTOList.add(new GradeDTO((Integer) objects1[0], (Integer) objects1[1],(String) objects1[3],
                (Integer) objects1[4],(String) objects1[2])));
        return new StudentDateWithGrades(AllDate.getDatesByMonthYear(year, month), gradeDTOList);
    }
}
