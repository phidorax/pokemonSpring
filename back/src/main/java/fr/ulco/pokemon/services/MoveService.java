package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.MoveNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewMoveDTO;
import fr.ulco.pokemon.model.dto.out.MoveDTO;
import io.vavr.control.Either;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

public interface MoveService {
    Optional<MoveDTO> findById(final Long id);

    Either<MoveNotFoundException, MoveDTO> findByName(final String name);

    Collection<String> findNames();

    Optional<URI> createMove(final NewMoveDTO newMove);

    Optional<URI> editMove(Long id, final NewMoveDTO newMove);

    Boolean deleteMove(Long id);
}
