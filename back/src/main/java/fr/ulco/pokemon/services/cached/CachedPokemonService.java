package fr.ulco.pokemon.services.cached;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonTypeDTO;
import fr.ulco.pokemon.services.PokemonService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class CachedPokemonService implements PokemonService {
    
    private final PokemonService PokemonService;
    
    @Override
    public Optional<PokemonTypeDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Either<PokemonNotFoundException, PokemonTypeDTO> findByName(String name) {
        return null;
    }

    @Override
    public Collection<String> findNames() {
        return null;
    }

    @Override
    public Optional<URI> createPokemon(NewPokemonDTO newPokemon) {
        return Optional.empty();
    }
}
