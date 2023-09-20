package ru.spb.rtkdiary.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.spb.rtkdiary.Request.TeacherRequest;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Teachers;
import ru.spb.rtkdiary.models.WeekDays;
import ru.spb.rtkdiary.utils.GroupSubjectTeacherWeek;
import ru.spb.rtkdiary.utils.ListSubjectIdAndGroupId;
import ru.spb.rtkdiary.utils.TeacherDTOSHKA;

import java.util.List;
@Component
public class TeacherDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(TeacherDTOSHKA teacherDTOSHKA){
        String sql1 = "INSERT INTO teachers (name) VALUES (?)";
        jdbcTemplate.update(sql1, teacherDTOSHKA.getName());
        Long teacherId = jdbcTemplate.queryForObject("SELECT currval(pg_get_serial_sequence('teachers', 'id'))", Long.class);
        String sql3 = "INSERT INTO tea_sub_group (sub_id,tea_id,group_id,week_id) values (?,?,?,?)";

        List<GroupSubjectTeacherWeek> list = teacherDTOSHKA.getGroupSubjectTeacherWeekList();
        list.forEach(groupSubjectTeacherWeek -> jdbcTemplate.update(sql3,groupSubjectTeacherWeek.getSubjectId(),
                teacherId, groupSubjectTeacherWeek.getGroupId(),
                groupSubjectTeacherWeek.getWeekId()));
    }
    public void update(TeacherDTOSHKA teacherDTOSHKA){
        String sql1="update teachers set name=? where id=?";
        String sql2="delete from tea_sub_group where tea_id = ?;";
        String sql3="INSERT INTO tea_sub_group (sub_id,tea_id,group_id,week_id) values (?,?,?,?)";
        jdbcTemplate.update(sql1,teacherDTOSHKA.getName(),teacherDTOSHKA.getId());
        jdbcTemplate.update(sql2,teacherDTOSHKA.getId());
        List<GroupSubjectTeacherWeek> groupSubjectTeacherWeekList = teacherDTOSHKA.getGroupSubjectTeacherWeekList();
        groupSubjectTeacherWeekList.forEach(groupSubjectTeacherWeek -> jdbcTemplate.update(sql3,groupSubjectTeacherWeek.getSubjectId(),
                teacherDTOSHKA.getId(),
                groupSubjectTeacherWeek.getGroupId(),groupSubjectTeacherWeek.getWeekId()));

    }


}
