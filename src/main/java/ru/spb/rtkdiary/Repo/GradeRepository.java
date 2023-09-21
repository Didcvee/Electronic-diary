package ru.spb.rtkdiary.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spb.rtkdiary.models.Grade;

import java.util.List;
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query(name = "Grade.findGradeByTeachersIdAndSubjectsIdAndGroupIdAndDateYearAndDateMonth")
    List<Grade> findGradeByTeachersIdAndSubjectsIdAndGroupIdAndDateYearAndDateMonth(@Param("teachersId") int teachersId,
                                                                                    @Param("subjectsId") int subjectsId,
                                                                                    @Param("groupId") int groupId,
                                                                                    @Param("year") int year,
                                                                                    @Param("month") int month);
}