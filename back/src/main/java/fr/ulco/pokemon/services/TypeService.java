package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.TypeNotFoundException;
import fr.ulco.pokemon.model.dto.out.TypePokemonDTO;
import io.vavr.control.Either;

import java.util.Collection;
import java.util.Optional;

public interface TypeService {
    Optional<TypePokemonDTO> findById(final Long id);

    Either<TypeNotFoundException, TypePokemonDTO> findByName(final String name);

    Collection<String> findNames();
}
