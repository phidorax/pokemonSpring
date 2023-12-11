# Pokémon Spring

Ce projet a pour objectif la conception d'un "Pokédex" soit une encyclopédie de Pokémon, des entités avec diverses caractéristiques. Ceux-ci pourront être ajoutés, modifiés où supprimés par un utilisateur authentifié.

Il dispose d'une partie back-end développé avec le framework Spring et une base de données sous PostgreSQL, ainsi qu'une partie front-end développée avec VueJS.

### Groupe

- DETEUF Rémi
- FAUQUET Loïc
- GRARD Axel

## Schéma de l'infrastructure n-tier
[![Schéma](schema.svg)](https://app.diagrams.net/?src=about#Hphidorax%2FpokemonSpring%2Fmaster%2Fschema.drawio "Voir le Schéma sur draw.io")

## Back-end

### Description

La partie back-end est une API permettant de créer des utilisateurs et de gérer des Pokémon.

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

Lancer le front-end en mode développement :

```cd front
npm install
npm run dev```

Faire un build du front-end :

```npm run build```