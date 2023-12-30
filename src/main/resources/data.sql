delete from userinfo;
delete from appointment;
delete from review;
delete from message;

INSERT INTO userinfo (name, password, roles, country, languages, bio)
VALUES ('John', '$2a$10$GglJYDGHtabzwxbbeFfhle7A/W5z9OhHcPCj1RB2dUJyKItFW/d5K', 'ROLE_ADMIN', 'UK', 'ENGLISH,SPANISH',
        'Experienced administrator with a deep passion for languages. I believe in the power of effective communication to bring people together.'),
       ('Marko', '$2a$10$OquQc.91.x9fjj0dvIjIseAQNZ2v1fxQ7MWqSqe.GS9iHxmarR5JC', 'ROLE_USER', 'CROATIA', 'CROATIAN,ENGLISH',
        'A language enthusiast hailing from the beautiful country of Croatia. I find joy in exploring the intricacies of different languages and cultures.'),
       ('Ivan', '$2a$10$tFhJLvN30D5GDAB9Br25oe/sgFMAylhI7WG93yoSiFfH4j3kueNS2', 'ROLE_USER', 'RUSSIA', 'RUSSIAN,GERMAN',
        'Greetings from Russia! As a multilingual individual, I enjoy the richness that each language brings to our understanding of the world.'),
       ('Luca', '$2a$10$IWduYD5rMO..tvXAcNmh9eEBfIX0TQIqver1xfzZ8o7XoJ2hyWg16', 'ROLE_USER', 'ITALY', 'ITALIAN,SPANISH,FRENCH,ENGLISH',
        'Ciao! I am Luca, a polyglot from Italy. I find great fulfillment in embracing the diversity of languages, connecting with people globally.'),
       ('Guillaume', '$2a$10$ORdfsjo83rSJPZpMLfFTZu48dpPoX2j.8rOgpnBDiEAbOnfNpXBUG', 'ROLE_USER', 'FRANCE', 'FRENCH,ENGLISH',
        'Bonjour! I am Guillaume, a French speaker with a profound love for languages. Join me on this linguistic journey to explore the beauty of expression.'),
       ('Scott', '$2a$10$caXKQZmIupe2cQoA6NWGtux9thQf21Oo4p2lgOZIEooftN0nWirsy', 'ROLE_USER', 'CANADA', 'ENGLISH,FRENCH,RUSSIAN',
        'Hello from Canada! I am Scott, a Canadian with a passion for multiple languages. Let us celebrate the richness of linguistic diversity together.'),
       ('Dieter', '$2a$10$g2aBOh9uUXgPr44RTPtAL.GrXYMP0B9moKDyRb/VMIawHW7yM1wwa', 'ROLE_USER', 'GERMANY', 'GERMAN,SPANISH,RUSSIAN,CROATIAN,ENGLISH',
        'Hallo! I am Dieter, a polyglot from Germany. My fascination with languages knows no bounds, and I am eager to share this linguistic adventure with you.'),
       ('Alejandro', '$2a$10$3VELFWSNsTbIeqW6l2ZjMuQlc0g.GWZqof6.HyJ4DlVuB5Mo55ST.', 'ROLE_USER', 'SPAIN', 'SPANISH,ENGLISH,FRENCH,ITALIAN',
        '¡Hola! I am Alejandro, a Spanish individual deeply passionate about languages. Let us explore the beauty of communication in its many forms.');

INSERT INTO appointment (user_id_1, user_id_2, apt_date, description)
VALUES ('2', '6', '2023-05-06', 'We are gonna get to know each other.'),
       ('3', '7', '2023-01-01', 'We are gonna talk some German.'),
       ('6', '8', '2024-03-04', 'Online coffee is always a good idea.'),
       ('3', '6', '2024-07-08', 'We have to practice our Russian before it gets stale.'),
       ('4', '7', '2024-06-05', 'Non parliamo inglese pero italiano e molto buono.');

INSERT INTO review (reviewer_id, reviewee_id, stars, review_date, review_text)
VALUES ('2', '6', '5', '2022-02-06', 'Great guy. Wish I had met him sooner. Great speaker and teacher!'),
       ('3', '7', '4', '2022-08-01',
        'We got off of the wrong foot but the guy is still great. Would not recommend to complete beginners.'),
       ('6', '8', '1', '2022-03-04',
        'Total prick. Knowing four languages does not mean you should boast all the time. Never again.'),
       ('3', '6', '5', '2021-12-08',
        'I learned more in two sessions with him than in my whole highschool. He helped me get a certification, awesome dude!'),
       ('4', '7', '3', '2021-10-05', 'He is a bit rusty on his english but means well. Perhaps he will get better.');

INSERT INTO message (sender_id, receiver_id, date_sent, message_text)
VALUES ('2', '6', '2022-06-02 12:00:00', 'Hey man. Wanna meet and talk?'),
       ('6', '2', '2022-06-02 15:30:00', 'Sure. I work a 9-5, would Tuesday at six be ok?'),
       ('2', '6', '2022-06-02 18:45:00', 'Definitely. I will let you know if plans change.'),
       ('2', '6', '2022-06-03 09:15:00', 'Skype or Zoom?'),
       ('6', '2', '2022-06-03 14:00:00', 'Zoom is great, I will send you a message on Zoom that day. Cheers!'),

       ('6', '1', '2022-05-29 12:00:00', 'I know we got off of the wrong foot, sorry for that.'),
       ('1', '6', '2022-05-30 15:30:00', 'No problem dude'),
       ('1', '6', '2022-05-30 18:45:00', 'I am just worried that will repeat in the future, I have a nasty temper.'),
       ('6', '1', '2022-06-01 19:15:00', 'Well you will have to work on your patience...'),
       ('6', '1', '2022-06-01 19:15:30', 'You will get there eventually.');
