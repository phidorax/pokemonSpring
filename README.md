# Pokémon Spring

Ce projet a pour objectif la conception d'un "Pokédex" soit un regroupement de "Pokemon", des entités avec diverses caractéristiques, ceux-ci pourront être ajouté, modifié où supprimer par un utilisateur authentifié.

Il dispose d'une partie back-end développé avec le framework Spring, une partie front-end développé avec VueJS et une base de données sous PostgreSQL.


### Groupe

- DETEUF Rémi
- FAUQUET Loïc
- GRARD Axel

### Back-end

Une image docker est fournie avec cette base de projet.

Exécuter `docker compose up` pour lancer celui-ci. Attention : Par défaut, il n'y a pas de volume, donc pas de persistance des données. (Si le docker est détruit, les données également). Nous avons utilisé utilisé [docker-compose-postgres](https://github.com/felipewom/docker-compose-postgres) pour mettre en place facilement ce genre d'outil.

Lancer `psql postgresql://username:password@localhost:5432/default_database` pour se connecter.

Dialecte PostgreSQL : `org.hibernate.dialect.PostgreSQLDialect`

### Front-end

### Description

Le projet back est une API permettant de créer des utilisateurs et de gérer des pokemons.

### Utilisation

Une image docker est fournis avec cette base de projet.

Executer `docker compose up` pour lancer celui-ci. Attention par défaut, il n'y a pas de volume, donc pas de persitance
de données. (Si le docker est détruit, la donnée également). Nous avons utilisé
ce [projet](https://github.com/felipewom/docker-compose-postgres) pour mettre en place facilement ce genre d'outil.

Lancer `psql postgresql://username:password@localhost:5432/default_database` pour se connecter à la base de données.


## Front-end

### Description

Le projet front dispose de diverse boutons menant vers des formulaires permettant une interface vers des requêtes fectch faite sur les endpoints de l'API.

### Utilisation

Une fois à l'interieur du dossier front (via `cd front`), il suffit de mettre a jour les packages avec `npm install` puis on peut lancer le projet sans compilation avec `npm run dev`.