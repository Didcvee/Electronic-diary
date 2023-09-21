package ru.spb.rtkdiary.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.models.Peoples;
@Repository
public interface StudentsRepository extends JpaRepository<Peoples,Integer> {


}
