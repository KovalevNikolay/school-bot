CREATE TABLE IF NOT EXISTS school_db.lesson_time
(
    id SERIAL PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
);