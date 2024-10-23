package ru.kovalev.schoolbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kovalev.schoolbot.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}