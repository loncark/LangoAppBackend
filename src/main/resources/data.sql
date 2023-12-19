delete from userinfo;

insert into userinfo (name, password, roles)
values ( 'John', '$2a$10$jfi6MfM2yLz9/EXq6MXDEefljQUBJsz7zrbHmduuqnl.ux9KPgTYi', 'ROLE_ADMIN' ),
       ( 'Mark', '$2a$10$lngh53VQJRIeY1hOHaQEZ.M/7Obv8WTV2D6g/IbjodfgH7pf/ocZC', 'ROLE_USER' ),
       ( 'Luke', '$2a$10$d8hKlo4xzuXyrM2vXBJ8yeiBjmMJB2cg/.uQSOE5ZoytIJDXsOaCC', 'ROLE_USER' ),
       ( 'Matthew', '$2a$10$fee/dAG7dZ2CtFTEsYQRIuMPGv7e.egp87t8ItXTVBHO9lXfh1QIe', 'ROLE_USER' );