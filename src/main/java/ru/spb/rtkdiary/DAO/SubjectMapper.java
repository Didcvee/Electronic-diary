package ru.spb.rtkdiary.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.spb.rtkdiary.models.Subjects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subjects> {
    @Override
    public Subjects mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subjects subjects = new Subjects();
        subjects.setId(rs.getInt("sub_id"));
        return subjects;
    }
}
