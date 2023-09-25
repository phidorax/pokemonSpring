package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.AbilityNotFoundException;
import fr.ulco.pokemon.model.dto.out.AbilityDTO;
import io.vavr.control.Either;

import java.util.Optional;

public interface AbilityService {
    Optional<AbilityDTO> findById(final Long id);

    Either<AbilityNotFoundException, AbilityDTO> findByName(final String name);
}
