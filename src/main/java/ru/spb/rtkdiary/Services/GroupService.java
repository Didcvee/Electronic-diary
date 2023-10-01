package ru.spb.rtkdiary.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DTO.GroupDTO;
import ru.spb.rtkdiary.Exception.UserNotFoundException;
import ru.spb.rtkdiary.Repo.GroupRepository;
import ru.spb.rtkdiary.models.Group;
import ru.spb.rtkdiary.models.Peoples;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class GroupService {
    private final GroupRepository groupRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @Transactional
    public void save(GroupDTO groupDTO){
        groupRepository.save(new Group(groupDTO.getName())); //
    }
    public List<GroupDTO> findAll(){
        List<GroupDTO> groupDTO = new ArrayList<>();
        groupRepository.findAll().forEach(group -> groupDTO
                .add(new GroupDTO(group.getId(),group.getName())));
        if(groupDTO.isEmpty()) throw new UserNotFoundException("Группы не найдены");
        return groupDTO; //
    }
    public GroupDTO findById(int id){
        Query query = entityManager.createNativeQuery("select g.name, g.id,p.id as peopleId,p.name as peopleName from group_ g join people p on g.id=p.group_id where g.id=:gro").setParameter("gro",id);
        List<Object[]> objects = query.getResultList();
        List<Peoples> peoples = new ArrayList<>();
        GroupDTO groupDTO = new GroupDTO((Integer)objects.get(0)[1], (String)objects.get(0)[0]);
        objects.forEach(objects1 -> peoples.add(new Peoples((Integer) objects1[2],(String) objects1[3])));
        groupDTO.setPeoples(peoples);
        if(groupDTO.getName()==null) throw new UserNotFoundException("Группы не найдены");
        return groupDTO; //
    }
    @Transactional
    public void update(GroupDTO groupDTO){
        Group existingGroup = entityManager.find(Group.class, groupDTO.getId());
        existingGroup.setName(groupDTO.getName());
        entityManager.merge(existingGroup); //
    }
    @Transactional
    public void deleteGroup(GroupDTO groupDTO){
        entityManager.createNativeQuery("delete from group_ where id=:id").setParameter("id",groupDTO.getId()).executeUpdate();
        //
    }
}
