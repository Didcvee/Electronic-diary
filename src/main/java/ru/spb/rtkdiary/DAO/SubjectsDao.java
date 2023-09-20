package ru.spb.rtkdiary.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spb.rtkdiary.models.Subjects;

import java.util.*;

@Component
public class SubjectsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubjectsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Subjects> findTeachersSub(int id){
        List<Subjects> list = jdbcTemplate.query("select subjects.id, subjects.name from subjects join tea_sub_group on subjects.id = tea_sub_group.sub_id join teachers on tea_sub_group.tea_id = teachers.id where tea_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Subjects.class));
        Set<Subjects> uniq = new HashSet<>();
        for (int i=0;i<list.size();i++){
            uniq.add(list.get(i));
        }
        List<Subjects> subjects = List.copyOf(uniq);
        System.out.println(subjects);

        return subjects;
    }
}
