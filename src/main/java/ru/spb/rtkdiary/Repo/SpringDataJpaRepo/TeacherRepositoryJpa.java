package ru.spb.rtkdiary.Repo.SpringDataJpaRepo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.spb.rtkdiary.models.Teachers;

@Repository
public interface TeacherRepositoryJpa extends JpaRepository<Teachers,Integer> {

    Teachers findById(int id);

}
