package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.AuthorNotFoundException;
import fr.ulco.pokemon.model.dao.AuthorRepository;
import fr.ulco.pokemon.model.dto.in.NewAuthorDTO;
import fr.ulco.pokemon.model.dto.out.AuthorDTO;
import fr.ulco.pokemon.model.entities.AuthorEntity;
import fr.ulco.pokemon.model.mappers.AuthorMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Optional<AuthorDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Either<AuthorNotFoundException, AuthorDTO> findByName(String name) {
        return authorRepository.findByName(name)
                .toEither(new AuthorNotFoundException())
                .map(AuthorMapper::toDto);
    }

    @Override
    public Optional<AuthorDTO> createAuthor(NewAuthorDTO newAuthor) {
        return Optional.empty();
    }

    @Override
    public Collection<String> findNames() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorEntity::getName)
                .toList();
    }
}
