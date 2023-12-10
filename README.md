# Pokémon Spring

Ce projet a pour objectif la conception d'un "Pokédex" soit un regroupement de "Pokemon", des entités avec diverses caractéristiques, ceux-ci pourront être ajouté, modifié où supprimer par un utilisateur authentifié.

Il dispose d'une partie back-end développé avec le framework Spring, une partie front-end développé avec VueJS et une base de données sous PostgreSQL.


### Groupe

- DETEUF Rémi
- FAUQUET Loïc
- GRARD Axel


## Schéma de l'infrastructure n-tier
[![Schéma](schema.svg)](https://app.diagrams.net/?src=about#Hphidorax%2FpokemonSpring%2Fmaster%2Fschema.drawio "Voir le Schéma sur draw.io")

## Back-end

### Description

Le projet back est une API permettant de créer des utilisateurs et de gérer des pokemons.

### Utilisation

Une image docker est fournie avec cette base de projet pour créer la base de données.

Exécuter `docker compose up` pour lancer celui-ci. Attention : Par défaut, il n'y a pas de volume, donc pas de persistance des données.
(Si le docker est détruit, les données également).
Nous avons utilisé [le projet docker-compose-postgres](https://github.com/felipewom/docker-compose-postgres) pour mettre en place facilement ce genre d'outil.

La base de données crée par le docker compose à toutes les tables nécessaires avec des données dans les tables.

Lancer `psql postgresql://username:password@localhost:5432/pokemon_database` pour se connecter à la base de données.

Dialecte PostgreSQL utilisé : `org.hibernate.dialect.PostgreSQLDialect`

## Front-end

### Description

Le projet front dispose de diverse boutons menant vers des formulaires permettant une interface vers des requêtes fetch faite sur les endpoints de l'API.

### Utilisation

Une fois à l'interieur du dossier front (via `cd front`), il suffit de mettre a jour les packages avec `npm install` puis on peut lancer le projet sans compilation avec `npm run dev`.