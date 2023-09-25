package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import io.vavr.control.Either;

import java.util.Collection;
import java.util.Optional;

public interface PokemonService {
    Optional<PokemonDTO> findById(final Long id);

    Either<PokemonNotFoundException, PokemonDTO> findByName(final String name);

    Collection<String> findNames();

    Optional<PokemonDTO> createPokemon(final NewPokemonDTO newPokemon);
}
