package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonSimpleDTO;
import fr.ulco.pokemon.model.dto.out.PokemonTypeDTO;
import io.vavr.control.Either;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

public interface PokemonService {
    Optional<PokemonTypeDTO> findById(final Long id);

    Either<PokemonNotFoundException, PokemonTypeDTO> findByName(final String name);

    Collection<PokemonSimpleDTO> findNames();

    Optional<URI> createPokemon(final NewPokemonDTO newPokemon);
}
