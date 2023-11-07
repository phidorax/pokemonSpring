package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.model.dto.out.TypePokemonDTO;
import fr.ulco.pokemon.model.entities.TypeEntity;

import java.util.ArrayList;
import java.util.List;

public class TypeMapper {

    public static TypePokemonDTO toDto(final TypeEntity entity) {
        List<PokemonDTO> pokemons = new ArrayList<>();
        entity.getPokemons().forEach(pokemon -> pokemons.add(PokemonMapper.toDto(pokemon)));
        return new TypePokemonDTO(entity.getId(), entity.getName(), pokemons);
    }
}
