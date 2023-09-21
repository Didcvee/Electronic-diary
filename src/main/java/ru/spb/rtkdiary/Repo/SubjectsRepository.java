package ru.spb.rtkdiary.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.models.Subjects;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects,Integer> {
}
