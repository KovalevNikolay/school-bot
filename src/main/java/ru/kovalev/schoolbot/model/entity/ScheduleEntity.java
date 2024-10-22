package ru.kovalev.schoolbot.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kovalev.schoolbot.model.DayOfWeek;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEntity implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discipline_id")
    private DisciplineEntity discipline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "classroom_id")
    private ClassRoomEntity classRoom;
}
