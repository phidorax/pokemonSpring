package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.AbilityDTO;
import fr.ulco.pokemon.model.entities.AbilityEntity;

public class AbilityMapper {

    public static AbilityDTO toDto(final AbilityEntity entity) {
        return new AbilityDTO(entity.getId(), entity.getName(), entity.getDescription());
    }
}
