package ru.spb.rtkdiary.Repo;

import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Exception.UserNotFoundException;
import ru.spb.rtkdiary.models.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public List<StudentDTO> findAll() throws UserNotFoundException{
        String sql = "select p.id as id, p.name as name, g.name as groupName, g.id as groupId from student p inner join group_ g on p.group_id=g.id";
        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();
        if(list.isEmpty()) throw new UserNotFoundException("Студенты не найдены");
        List<StudentDTO> studentDTOList = new ArrayList<>();
        list.forEach(peoples -> studentDTOList.add(new StudentDTO((Integer) peoples[0], (String) peoples[1],new Group((String)peoples[2],(Integer)peoples[3]))));
        return studentDTOList; //
    }

}
