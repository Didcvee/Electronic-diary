package ru.spb.rtkdiary.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spb.rtkdiary.Request.TeacherRequest;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.utils.ListSubjectIdAndGroupId;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Group> findGroupByTeaIdAndSubId(int tea_id, int sub_id){
        List<Group> list = jdbcTemplate.query("select group_.id, group_.name from group_ join tea_sub_group " +
                        "on group_.id = tea_sub_group.group_id where tea_sub_group.tea_id=? and tea_sub_group.sub_id=?",
                new Object[]{tea_id,sub_id}, new BeanPropertyRowMapper<>(Group.class));
        return list;
    }
    public void save(TeacherRequest teacherRequest){
        String sql1 = "INSERT INTO teachers (name) VALUES (?)";
        jdbcTemplate.update(sql1, teacherRequest.getName());
        String sql3 = "INSERT INTO tea_sub_group (sub_id,tea_id,group_id) values (?,?,?)";
        List<ListSubjectIdAndGroupId> list = teacherRequest.getListSubjectIdAndGroupId();
        list.forEach(listSubjectIdAndGroupId -> jdbcTemplate.update(sql3,listSubjectIdAndGroupId.getSubjectId(),
                //Здесь айди учителя должно быть,
                listSubjectIdAndGroupId.getGroupId()));

    }


}
