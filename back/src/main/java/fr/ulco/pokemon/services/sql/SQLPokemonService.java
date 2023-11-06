package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dao.PokemonRepository;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonTypeDTO;
import fr.ulco.pokemon.model.entities.PokemonEntity;
import fr.ulco.pokemon.model.mappers.PokemonMapper;
import fr.ulco.pokemon.services.PokemonService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLPokemonService implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public Optional<PokemonTypeDTO> findById(Long id) {
        return pokemonRepository.findById(id).map(PokemonMapper::toDto);
    }

    @Override
    public Either<PokemonNotFoundException, PokemonTypeDTO> findByName(String name) {
        return pokemonRepository.findByName(name)
                .toEither(new PokemonNotFoundException())
                .map(PokemonMapper::toDto);
    }

    @Override
    public Collection<String> findNames() {
        return pokemonRepository.findAll().stream().map(PokemonEntity::getName).toList();
    }

    @Override
    public Optional<URI> createPokemon(NewPokemonDTO newPokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setName(newPokemon.name());
        pokemonEntity.setHp(newPokemon.hp());
        pokemonEntity.setAttack(newPokemon.attack());
        pokemonEntity.setDefense(newPokemon.defense());
        pokemonEntity.setSpecialAttack(newPokemon.specialAttack());
        pokemonEntity.setSpecialDefense(newPokemon.specialDefense());
        pokemonEntity.setSpeed(newPokemon.speed());
        var saved = pokemonRepository.save(pokemonEntity);
        return Optional.of(URI.create("/pokemons/" + saved.getId()));
    }
}
