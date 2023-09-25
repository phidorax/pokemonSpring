package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.TypeDTO;
import fr.ulco.pokemon.model.entities.TypeEntity;

public class TypeMapper {

    public static TypeDTO toDto(final TypeEntity entity) {
        return new TypeDTO(entity.getId(), entity.getName());
    }
}
