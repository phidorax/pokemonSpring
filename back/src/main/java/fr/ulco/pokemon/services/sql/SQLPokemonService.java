package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dao.PokemonRepository;
import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.model.entities.PokemonEntity;
import fr.ulco.pokemon.model.mappers.PokemonMapper;
import fr.ulco.pokemon.services.PokemonService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLPokemonService implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public Optional<PokemonDTO> findById(Long id) {
        return pokemonRepository.findById(id).map(PokemonMapper::toDto);
    }

    @Override
    public Either<PokemonNotFoundException, PokemonDTO> findByName(String name) {
        return pokemonRepository.findByName(name)
                .toEither(new PokemonNotFoundException())
                .map(PokemonMapper::toDto);
    }

    @Override
    public Collection<String> findNames() {
        return pokemonRepository.findAll().stream().map(PokemonEntity::getName).toList();
    }
}
