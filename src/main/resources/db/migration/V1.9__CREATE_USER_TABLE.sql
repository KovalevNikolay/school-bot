CREATE TABLE IF NOT EXISTS school_db.user_table
(
    id BIGINT PRIMARY KEY,
    firstname VARCHAR(128),
    lastname VARCHAR(128),
    username VARCHAR(128),
    bio VARCHAR(128),
    role VARCHAR(64),
    role_id INT
);