DROP TABLE IF EXISTS project_worker;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS worker;

CREATE TABLE worker (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000),
    birthday DATE,
    level ENUM('Trainee', 'Junior', 'Middle', 'Senior'),
    salary INT,
    CONSTRAINT CK_worker_name CHECK (CHAR_LENGTH(name) > ? AND CHAR_LENGTH(name) <= ?),
    CONSTRAINT CK_worker_birthday CHECK (birthday > ?),
    CONSTRAINT CK_worker_salary CHECK (salary >= ? AND salary <= ?)
);

CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000),
    CONSTRAINT CK_client_name CHECK (CHAR_LENGTH(name) > ? AND CHAR_LENGTH(name) <= ?)
);

CREATE TABLE project (
    id IDENTITY PRIMARY KEY,
    client_id BIGINT,
    start_date DATE,
    finish_date DATE,
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE project_worker (
    project_id BIGINT,
    worker_id BIGINT,
    PRIMARY KEY (project_id, worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);