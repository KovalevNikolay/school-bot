CREATE TABLE IF NOT EXISTS school_db.lesson
(
    id SERIAL PRIMARY KEY,
    lesson_number INT NOT NULL,
    lesson_time_id INT REFERENCES school_db.lesson_time (id)
);