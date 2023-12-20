delete from userinfo;
delete from appointment;
delete from review;

INSERT INTO userinfo (name, password, roles, country, languages, bio)
VALUES
    ('John', 'johnpassword', 'ROLE_ADMIN', 'UK', 'ENGLISH,SPANISH', 'Experienced administrator with a deep passion for languages. I believe in the power of effective communication to bring people together.'),
    ('Marko', 'markopassword', 'ROLE_USER', 'CROATIA', 'CROATIAN,ENGLISH', 'A language enthusiast hailing from the beautiful country of Croatia. I find joy in exploring the intricacies of different languages and cultures.'),
    ('Ivan', 'ivanpassword', 'ROLE_USER', 'RUSSIA', 'RUSSIAN,GERMAN', 'Greetings from Russia! As a multilingual individual, I enjoy the richness that each language brings to our understanding of the world.'),
    ('Luca', 'lucapassword', 'ROLE_USER', 'ITALY', 'ITALIAN,SPANISH,FRENCH,ENGLISH', 'Ciao! I am Luca, a polyglot from Italy. I find great fulfillment in embracing the diversity of languages, connecting with people globally.'),
    ('Guillaume', 'guillaumepassword', 'ROLE_USER', 'FRANCE', 'FRENCH,ENGLISH', 'Bonjour! I am Guillaume, a French speaker with a profound love for languages. Join me on this linguistic journey to explore the beauty of expression.'),
    ('Scott', 'scottpassword', 'ROLE_USER', 'CANADA', 'ENGLISH,FRENCH,RUSSIAN', 'Hello from Canada! I am Scott, a Canadian with a passion for multiple languages. Let us celebrate the richness of linguistic diversity together.'),
    ('Dieter', 'dieterpassword', 'ROLE_USER', 'GERMANY', 'GERMAN,SPANISH,RUSSIAN,CROATIAN,ENGLISH', 'Hallo! I am Dieter, a polyglot from Germany. My fascination with languages knows no bounds, and I am eager to share this linguistic adventure with you.'),
    ('Alejandro', 'alejandropassword', 'ROLE_USER', 'SPAIN', 'SPANISH,ENGLISH,FRENCH,ITALIAN', '¡Hola! I am Alejandro, a Spanish individual deeply passionate about languages. Let us explore the beauty of communication in its many forms.');

INSERT INTO appointment (user_id_1, user_id_2, apt_date, description)
VALUES
    ('2', '6', '2023-05-06', 'We are gonna get to know each other.'),
    ('3', '7', '2023-01-01', 'We are gonna talk some German.'),
    ('6', '8', '2024-03-04', 'Online coffee is always a good idea.'),
    ('3', '6', '2024-07-08', 'We have to practice our Russian before it gets stale.'),
    ('4', '7', '2024-06-05', 'Non parliamo inglese pero italiano e molto buono.');

INSERT INTO review (reviewer_id, reviewee_id, stars, review_date, review_text)
VALUES
    ('2', '6', '5', '2022-02-06', 'Great guy. Wish I had met him sooner. Great speaker and teacher!'),
    ('3', '7', '4', '2022-08-01', 'We got off of the wrong foot but the guy is still great. Would not recommend to complete beginners.'),
    ('6', '8', '1', '2022-03-04', 'Total prick. Knowing four languages does not mean you should boast all the time. Never again.'),
    ('3', '6', '5', '2021-12-08', 'I learned more in two sessions with him than in my whole highschool. He helped me get a certification, awesome dude!'),
    ('4', '7', '3', '2021-10-05', 'He is a bit rusty on his english but means well. Perhaps he will get better.');
