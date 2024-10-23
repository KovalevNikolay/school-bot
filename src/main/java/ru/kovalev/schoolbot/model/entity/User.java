package ru.kovalev.schoolbot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "school_db", name = "user_table")
public class User implements BaseEntity<Long> {

    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String bio;
}
