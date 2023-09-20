package ru.spb.rtkdiary.Repo.SpringDataJpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.rtkdiary.models.WeekDays;

@Repository
public interface WeekRepository extends JpaRepository<WeekDays,Integer> {
}
