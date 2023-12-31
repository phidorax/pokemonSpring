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
    public Optional<URI> createPokemon(final NewPokemonDTO newPokemon) {
        PokemonEntity pokemonEntity = new PokemonEntity();
        return fillPokemonEntity(newPokemon, pokemonEntity);
    }

    private Optional<URI> fillPokemonEntity(NewPokemonDTO newPokemon, PokemonEntity pokemonEntity) {
        pokemonEntity.setId(newPokemon.id());
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

    @Override
    public Optional<URI> editPokemon(Long id, final NewPokemonDTO newPokemon) {
        Optional<PokemonEntity> pokemonById = pokemonRepository.findById(id);
        if (pokemonById.isPresent()) {
            PokemonEntity pokemonEntity = pokemonById.get();
            return fillPokemonEntity(newPokemon, pokemonEntity);
        }
        return Optional.empty();
    }

    @Override
    public Boolean deletePokemon(Long id) {
        if (pokemonRepository.findById(id).isPresent()) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
