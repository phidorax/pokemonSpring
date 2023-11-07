package fr.ulco.pokemon.services.cached;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonSimpleDTO;
import fr.ulco.pokemon.model.dto.out.PokemonTypeDTO;
import fr.ulco.pokemon.services.PokemonService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class CachedPokemonService implements PokemonService {
    @Override
    public Optional<PokemonTypeDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Either<PokemonNotFoundException, PokemonTypeDTO> findByName(String name) {
        return null;
    }

    @Override
    public Collection<PokemonSimpleDTO> findNames() {
        return null;
    }

    @Override
    public Optional<URI> createPokemon(final NewPokemonDTO newPokemon) {
        return Optional.empty();
    }

    @Override
    public Optional<URI> editPokemon(Long id, final NewPokemonDTO newPokemon) {
        return Optional.empty();
    }

    @Override
    public Boolean deletePokemon(Long id) {
        return null;
    }
}
