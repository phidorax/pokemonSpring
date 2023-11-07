package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.PokemonNotFoundException;
import fr.ulco.pokemon.model.dao.PokemonRepository;
import fr.ulco.pokemon.model.dao.TypeRepository;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonSimpleDTO;
import fr.ulco.pokemon.model.dto.out.PokemonTypeDTO;
import fr.ulco.pokemon.model.entities.PokemonEntity;
import fr.ulco.pokemon.model.mappers.PokemonMapper;
import fr.ulco.pokemon.services.PokemonService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLPokemonService implements PokemonService {

    private final PokemonRepository pokemonRepository;
    private final TypeRepository typeRepository;

    @Override
    public Optional<PokemonTypeDTO> findById(Long id) {
        return pokemonRepository.findById(id).map(PokemonMapper::toTypeDto);
    }

    @Override
    public Either<PokemonNotFoundException, PokemonTypeDTO> findByName(String name) {
        return pokemonRepository.findByName(name)
                .toEither(new PokemonNotFoundException())
                .map(PokemonMapper::toTypeDto);
    }

    @Override
    public Collection<PokemonSimpleDTO> findNames() {
        return pokemonRepository.findAll().stream().map(PokemonMapper::toSimpleDto).toList();
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
        pokemonEntity.setTypes(new ArrayList<>());
        try {
            newPokemon.types().stream().toList().forEach(type -> pokemonEntity.getTypes().add(typeRepository.findByName(type.name()).get()));
        } catch (Exception e) {
            System.out.println("Type not found");
        }
        var saved = pokemonRepository.save(pokemonEntity);
        return Optional.of(URI.create("/pokemons/" + saved.getId()));
    }
}
