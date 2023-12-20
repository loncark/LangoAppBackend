CREATE TABLE IF NOT EXISTS userinfo
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    password  VARCHAR(100) NOT NULL,
    name      VARCHAR(100) NOT NULL,
    roles     VARCHAR(100) NOT NULL,
    country   VARCHAR(100),
    languages VARCHAR(255),
    bio       VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS appointment
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    user_id_1   INT,
    user_id_2   INT,
    apt_date    DATE,
    description VARCHAR(255),
    FOREIGN KEY (user_id_1) REFERENCES userinfo (id),
    FOREIGN KEY (user_id_2) REFERENCES userinfo (id)
);



