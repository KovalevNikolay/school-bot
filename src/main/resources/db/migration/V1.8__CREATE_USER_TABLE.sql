CREATE TABLE IF NOT EXISTS school_db.schedule
(
    id SERIAL PRIMARY KEY,
    day_of_week VARCHAR(128) NOT NULL,
    lesson_id INT REFERENCES school_db.lesson (id),
    discipline_id INT REFERENCES school_db.discipline (id),
    group_id INT REFERENCES school_db.group_table (id),
    teacher_id INT REFERENCES school_db.teacher (id),
    classroom_id INT REFERENCES school_db.class_room (id)
);