package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.model.entities.PokemonEntity;

public class PokemonMapper {

    public static PokemonDTO toDto(final PokemonEntity entity) {
        return new PokemonDTO(entity.getId(), entity.getName(), entity.getHp(), entity.getAttack(), entity.getDefense(),
                entity.getSpecialAttack(), entity.getSpecialDefense(), entity.getSpeed());
    }
}
