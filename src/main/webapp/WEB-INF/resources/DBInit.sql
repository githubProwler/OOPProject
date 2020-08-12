CREATE SCHEMA IF NOT EXISTS quizzapp;
USE quizzapp;

# DROP TABLE IF EXISTS usertypes;
CREATE TABLE IF NOT EXISTS usertypes
(
    id INT NOT NULL PRIMARY KEY,
    typename VARCHAR(45) NULL
);

# DROP TABLE users;
CREATE TABLE IF NOT EXISTS users
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    usertype INT NOT NULL,
    CONSTRAINT FOREIGN KEY users_usertypes_fk
        (usertype) REFERENCES usertypes(id)
);

CREATE TABLE IF NOT EXISTS quizzes
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(200) NOT NULL,
    user INT NOT NULL,
    randomized BOOLEAN NOT NULL DEFAULT FALSE,
    singlepage BOOLEAN NOT NULL DEFAULT TRUE,
    immediatecorrection BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT FOREIGN KEY quizzes_users_fk
        (user) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS questions
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(400) NOT NULL,
    answer VARCHAR(400) NOT NULL,
    blankindex INT DEFAULT -1,
    multiplechoice BOOLEAN NOT NULL DEFAULT FALSE,
    mcans1 NVARCHAR(50) DEFAULT NULL,
    mcans2 NVARCHAR(50) DEFAULT NULL,
    mcans3 NVARCHAR(50) DEFAULT NULL,
    mcans4 NVARCHAR(50) DEFAULT NULL,
    quizz INT NOT NULL,
    CONSTRAINT FOREIGN KEY questions_quizzes_pk
        (quizz) REFERENCES quizzes(id)
        ON DELETE CASCADE
);

DELETE FROM users WHERE 1 = 1;
SELECT * FROM usertypes;
SELECT * FROM users;
INSERT INTO usertypes (id, typename) VALUE(0, 'admin');
INSERT INTO usertypes (id, typename) VALUE(1, 'default');
INSERT INTO users (username, password, usertype) VALUE('admin', '21232f297a57a5a743894a0e4a801fc3', 0);
INSERT INTO users (username, password, usertype) VALUE('5', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('6', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('7', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('8', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('9', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('10', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('11', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('12', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('13', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('14', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('15', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('16', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('17', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('18', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('19', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('20', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('21', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('22', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('23', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('24', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('25', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('26', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('27', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('28', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('29', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('30', '1', 1);
INSERT INTO users (username, password, usertype) VALUE('31', '1', 1);