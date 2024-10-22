package ru.kovalev.schoolbot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements BaseEntity<Long> {

    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
}
