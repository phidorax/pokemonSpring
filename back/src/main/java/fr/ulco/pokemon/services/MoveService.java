package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.MoveNotFoundException;
import fr.ulco.pokemon.model.dto.out.MoveDTO;
import io.vavr.control.Either;

import java.util.Optional;

public interface MoveService {
    Optional<MoveDTO> findById(final Long id);

    Either<MoveNotFoundException, MoveDTO> findByName(final String name);
}
