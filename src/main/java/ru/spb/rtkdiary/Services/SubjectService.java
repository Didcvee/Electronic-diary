package ru.spb.rtkdiary.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.rtkdiary.DTO.SubjectsDTO;
import ru.spb.rtkdiary.Repo.SubjectsRepository;
import ru.spb.rtkdiary.models.Subjects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubjectService {
    private final SubjectsRepository subjectsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SubjectService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }
    @Transactional
    public void save(SubjectsDTO subjectsDTO){
        subjectsRepository.save(new Subjects(subjectsDTO.getName())); //
    }
    public List<SubjectsDTO> findAll(){
        List<SubjectsDTO> list = new ArrayList<>();
        subjectsRepository.findAll().forEach(subjects -> list
                .add(new SubjectsDTO(subjects.getId(),subjects.getName())));
        return list; //
    }
    public SubjectsDTO findById(int id){
        Subjects subjects = subjectsRepository.findById(id).orElse(null);
        return new SubjectsDTO(subjects.getId(), subjects.getName()); //
    }

    @Transactional
    public void update(SubjectsDTO subjectsDTO){
        Subjects subjects = entityManager.find(Subjects.class, subjectsDTO.getId());
        subjects.setName(subjectsDTO.getName());
        entityManager.merge(subjects); //
    }
    @Transactional
    public void deleteSubject(SubjectsDTO subjectsDTO){
        entityManager.createNativeQuery("delete from subjects where id=:id").setParameter("id",subjectsDTO.getId()).executeUpdate();
        //
    }
}
