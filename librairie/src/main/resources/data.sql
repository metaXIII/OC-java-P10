INSERT INTO MAISONEDITION(ID, NOM, SIRET)
VALUES (1, 'Maison edition test', 'SIRET Test');

INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE, MAXQUANTITE)
VALUES (1, 'Harry potter à l''école des sorciers', 'AUTEUR TEST', CURRENT_DATE(), 'ISBN TEST', 'EROTIQUE', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        1, 10);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE, MAXQUANTITE)
VALUES (2, 'Dragon Ball Z', 'AUTEUR TEST2', CURRENT_DATE(), 'ISBN TEST2', 'ADOLESCENT', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        5, 10);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE, MAXQUANTITE)
VALUES (3, 'Batman', 'AUTEUR TEST3', CURRENT_DATE(), 'ISBN TEST3', 'JEUNESSE', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        10, 10);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE, MAXQUANTITE)
VALUES (4, 'Percy Jackson à l''école des souris', 'AUTEUR TEST4', CURRENT_DATE(), 'ISBN TEST4', 'MANGA', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        15, 10);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE, MAXQUANTITE)
VALUES (5, 'Da Vinci Code', 'AUTEUR TEST5', CURRENT_DATE(), 'ISBN TEST5', 'HORREUR', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        20, 10);
