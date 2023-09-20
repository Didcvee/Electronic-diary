package ru.spb.rtkdiary.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Peoples;

import java.util.List;
@Component
public class PeopleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Peoples> findAll(){
        List<Peoples> list = jdbcTemplate.query("select * from people",
                new Object[]{}, new BeanPropertyRowMapper<>(Peoples.class));
        return list;
    }
}
