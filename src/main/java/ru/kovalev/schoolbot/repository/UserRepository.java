package ru.kovalev.schoolbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kovalev.schoolbot.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<Long, UserEntity> {
}
