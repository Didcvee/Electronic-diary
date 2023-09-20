package ru.spb.rtkdiary.Repo.SpringDataJpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.models.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {

}
