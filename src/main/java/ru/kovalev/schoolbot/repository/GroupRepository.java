package ru.kovalev.schoolbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovalev.schoolbot.model.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
