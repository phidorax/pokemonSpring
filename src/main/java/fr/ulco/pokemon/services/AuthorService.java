package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.AuthorNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewAuthorDTO;
import fr.ulco.pokemon.model.dto.out.AuthorDTO;
import io.vavr.control.Either;

import java.util.Collection;
import java.util.Optional;

public interface AuthorService {
    Optional<AuthorDTO> findById(final Long id);

    Either<AuthorNotFoundException, AuthorDTO> findByName(final String name);

    Optional<AuthorDTO> createAuthor(final NewAuthorDTO newAuthor);

    Collection<String> findNames();
}
