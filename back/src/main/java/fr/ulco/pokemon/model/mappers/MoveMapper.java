package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.MoveDTO;
import fr.ulco.pokemon.model.entities.MoveEntity;

public class MoveMapper {

    public static MoveDTO toDto(final MoveEntity entity) {
        return new MoveDTO(entity.getId(), entity.getName());
    }
}
