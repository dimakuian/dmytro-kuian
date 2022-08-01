INSERT INTO role (id, name)
VALUES (0, 'admin'),
       (1, 'client');


INSERT INTO invoice_status (id, name)
VALUES (0, 'created'),
       (1, 'paid'),
       (2, 'declined');


INSERT INTO language (id, short_name, full_name)
VALUES (1, 'EN', 'English'),
       (2, 'UA', 'Ukraine');


INSERT INTO shipping_status (id, name)
VALUES (0, 'created'),
       (1, 'confirmed'),
       (2, 'paid'),
       (3, 'preparing to ship'),
       (4, 'in the way'),
       (5, 'delivered'),
       (6, 'canceled');


INSERT INTO locality (id, name, latitude, longitude)
VALUES (1, 'Kiev department №1', 50.430152159229465, 30.400358449390378),
       (2, 'Lviv department №2', 49.85580301226521, 24.019571021514157),
       (3, 'Odesa department №3', 46.47743026963285, 30.700937087405833),
       (4, 'Ternopil department №4', 49.551989782699984, 25.57133777998255),
       (5, 'Rivne department №5', 50.612594968969354, 26.246152539988664),
       (6, 'Lutsk department №6', 50.73078011016832, 25.29546575120349),
       (7, 'Kovel department №7', 51.19893769525515, 24.678678351324603),
       (8, 'Chernivtsi department №8', 48.26790354152539, 25.928226407906312),
       (9, 'Khmelnytskyi department №9', 49.42123992709202, 26.989182060186323),
       (10, 'Vinnytsia department №10', 49.227985950780884, 28.45126010263941),
       (11, 'Zhytomyr department №11', 50.24725716515697, 28.680031580973704),
       (12, 'Dnipro department №12', 48.46696886348005, 34.97749206319865),
       (13, 'Zaporizhzhya department №13', 47.83373266474125, 35.15006225982181),
       (14, 'Mykolaiv department №14', 46.95604226441692, 32.032779749536004),
       (15, 'Ivano-Frankivsk department №15', 48.91735027526064, 24.7003315862229),
       (16, 'Mukachevo department №16', 48.444585453958084, 22.724035901090634);









