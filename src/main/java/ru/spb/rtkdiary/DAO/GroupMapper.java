package ru.spb.rtkdiary.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.spb.rtkdiary.models.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        return group;
    }
}
