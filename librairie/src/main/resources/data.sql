INSERT INTO MAISONEDITION(ID, NOM, SIRET)
VALUES (1, 'Maison edition test', 'SIRET Test');

INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE)
VALUES (1, 'nom test', 'AUTEUR TEST', CURRENT_DATE(), 'ISBN TEST', 'EROTIQUE', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        0);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE)
VALUES (2, 'nom test2', 'AUTEUR TEST2', CURRENT_DATE(), 'ISBN TEST2', 'ADOLESCENT', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        5);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE)
VALUES (3, 'nom test3', 'AUTEUR TEST3', CURRENT_DATE(), 'ISBN TEST3', 'JEUNESSE', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        10);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE)
VALUES (4, 'nom test4', 'AUTEUR TEST4', CURRENT_DATE(), 'ISBN TEST4', 'MANGA', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        15);
INSERT INTO LIVRE(ID, NOM, AUTEUR, DATEPARUTION, ISBN, CATEGORIE, MAISONEDITION, RESUME, QUANTITE)
VALUES (5, 'nom test5', 'AUTEUR TEST5', CURRENT_DATE(), 'ISBN TEST5', 'HORREUR', 1,
        'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusamus alias autem commodi
eligendi enim error impedit iste labore neque nobis nostrum odit perspiciatis, quisquam quod repellat rerum, temporibus vitae?',
        20);
