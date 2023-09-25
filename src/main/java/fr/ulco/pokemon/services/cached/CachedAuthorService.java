package fr.ulco.pokemon.services.cached;

import fr.ulco.pokemon.exceptions.AuthorNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewAuthorDTO;
import fr.ulco.pokemon.model.dto.out.AuthorDTO;
import fr.ulco.pokemon.services.AuthorService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class CachedAuthorService implements AuthorService {
    
    private final AuthorService authorService;
    
    @Override
    public Optional<AuthorDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Either<AuthorNotFoundException, AuthorDTO> findByName(String name) {
        return null;
    }

    @Override
    public Optional<AuthorDTO> createAuthor(NewAuthorDTO newAuthor) {
        return Optional.empty();
    }

    @Override
    public Collection<String> findNames() {
        return null;
    }
}
