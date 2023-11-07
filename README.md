# Pokémon Spring

Pokédex avec un back-end développé avec le framework Spring, un front-end développé avec VueJS et une base de données PostgreSQL.


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

```
cd front
npm install
npm run dev
```