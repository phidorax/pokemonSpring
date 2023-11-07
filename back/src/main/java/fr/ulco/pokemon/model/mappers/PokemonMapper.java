package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonSimpleDTO;
import fr.ulco.pokemon.model.dto.out.PokemonTypeDTO;
import fr.ulco.pokemon.model.dto.out.TypeDTO;
import fr.ulco.pokemon.model.entities.PokemonEntity;

import java.util.ArrayList;
import java.util.List;

public class PokemonMapper {

    public static PokemonTypeDTO toTypeDto(final PokemonEntity entity) {
        List<TypeDTO> types = new ArrayList<>();
        entity.getTypes().forEach(type -> types.add(new TypeDTO(type.getId(), type.getName())));
        return new PokemonTypeDTO(entity.getId(), entity.getName(), entity.getHp(), entity.getAttack(), entity.getDefense(),
                entity.getSpecialAttack(), entity.getSpecialDefense(), entity.getSpeed(), types);
    }

    public static PokemonDTO toDto(final PokemonEntity entity) {
        return new PokemonDTO(entity.getId(), entity.getName(), entity.getHp(), entity.getAttack(), entity.getDefense(),
                entity.getSpecialAttack(), entity.getSpecialDefense(), entity.getSpeed());
    }

    public static PokemonSimpleDTO toSimpleDto(final PokemonEntity entity) {
        return new PokemonSimpleDTO(entity.getId(), entity.getName());
    }
}
