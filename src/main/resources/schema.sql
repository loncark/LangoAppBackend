CREATE TABLE IF NOT EXISTS userinfo
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(100) NOT NULL,
    name     VARCHAR(100) NOT NULL,
    roles    VARCHAR(100) NOT NULL,
    country  VARCHAR(100),
    languages VARCHAR(255),
    bio    VARCHAR(300)
);


