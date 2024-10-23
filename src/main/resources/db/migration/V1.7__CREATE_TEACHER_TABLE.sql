CREATE TABLE IF NOT EXISTS school_db.teacher
(
    id SERIAL PRIMARY KEY,
    lastname VARCHAR(128) NOT NULL,
    firstname VARCHAR(128) NOT NULL,
    patronymic VARCHAR(128)
);