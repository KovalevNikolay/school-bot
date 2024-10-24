CREATE TABLE IF NOT EXISTS school_db.lesson
(
    id SERIAL PRIMARY KEY,
    lesson_number INT NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);