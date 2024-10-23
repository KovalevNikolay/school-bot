package ru.kovalev.schoolbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovalev.schoolbot.model.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
