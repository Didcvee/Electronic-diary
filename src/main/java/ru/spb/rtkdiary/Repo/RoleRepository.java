package ru.spb.rtkdiary.Repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.spb.rtkdiary.models.ERole;
import ru.spb.rtkdiary.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByERole(@Value("name") ERole name);
}
