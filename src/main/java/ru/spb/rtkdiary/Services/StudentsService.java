package ru.spb.rtkdiary.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DTO.StudentDTO;
import ru.spb.rtkdiary.Repo.StudentsRepository;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Peoples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentsService {
    private final StudentsRepository studentsRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<StudentDTO> findAll(){
        String sql = "select p.id as id, p.name as name, g.name as groupName, g.id as groupId from people p inner join group_ g on p.group_id=g.id";
        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        list.forEach(peoples -> studentDTOList.add(new StudentDTO((Integer) peoples[0], (String) peoples[1],new Group((String)peoples[2],(Integer)peoples[3]))));
        return studentDTOList; //
    }

    public StudentDTO findById(int id){
        Peoples peoples = studentsRepository.findById(id).orElse(null);
        return new StudentDTO(peoples.getId(), peoples.getName(),peoples.getGroup()); //
    }

    @Transactional
    public void save(StudentDTO studentDTO){
        Peoples peoples = new Peoples(studentDTO.getName(),studentDTO.getGroup());
        studentsRepository.save(peoples); //
    }

    @Transactional
    public void update(StudentDTO studentDTO){
        Peoples peoples = new Peoples(studentDTO.getId(),studentDTO.getName(),studentDTO.getGroup());
        studentsRepository.save(peoples); //
    }
    @Transactional
    public void deleteStudent(StudentDTO studentDTO){
        entityManager.createNativeQuery("delete from student where id=:id").setParameter("id",studentDTO.getId()).executeUpdate();
        //
    }
}
