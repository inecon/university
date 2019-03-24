DROP TABLE IF EXISTS students, groups, teachers, lectures  CASCADE;

CREATE TABLE students (
        id int NOT NULL,
        name varchar(30) NOT NULL,
        surName varchar(50) NOT NULL,
        gender varchar(6) NOT NULL,
        age int NOT NULL,
        group_id int NOT NULL,
        PRIMARY KEY (id)
);

CREATE TABLE groups (
        id int NOT NULL,
        title varchar(50) NOT NULL,
        description varchar(150) NOT NULL,
        PRIMARY KEY (id)
);
CREATE TABLE teachers (
        id int NOT NULL,
        name varchar(30) NOT NULL,
        surName varchar(50) NOT NULL,
        gender varchar(6) NOT NULL,
        age int NOT NULL,
        subject varchar(100) NOT NULL,
        PRIMARY KEY (id)
);
CREATE TABLE lectures (
        id int NOT NULL,
        date TIMESTAMP NOT NULL,
        subject varchar(50) NOT NULL,
        teacher_id int NOT NULL,
        group_id int NOT NULL,
        classroom int NOT NULL,
        PRIMARY KEY (id)
)