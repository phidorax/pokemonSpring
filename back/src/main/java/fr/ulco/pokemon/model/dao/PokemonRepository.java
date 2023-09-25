package fr.ulco.pokemon.model.dao;

import fr.ulco.pokemon.model.entities.AuthorEntity;
import fr.ulco.pokemon.model.entities.PokemonEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
    Option<PokemonEntity> findByName(final String name);
}
