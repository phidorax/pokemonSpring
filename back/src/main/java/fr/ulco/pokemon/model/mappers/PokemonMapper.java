package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.model.dto.out.TypeDTO;
import fr.ulco.pokemon.model.entities.PokemonEntity;

import java.util.ArrayList;
import java.util.List;

public class PokemonMapper {

    public static PokemonDTO toDto(final PokemonEntity entity) {
        List<TypeDTO> types = new ArrayList<>();
        entity.getTypes().forEach(type -> types.add(new TypeDTO(type.getId(), type.getName())));
        return new PokemonDTO(entity.getId(), entity.getName(), entity.getHp(), entity.getAttack(), entity.getDefense(),
                entity.getSpecialAttack(), entity.getSpecialDefense(), entity.getSpeed(), types);
    }
}
