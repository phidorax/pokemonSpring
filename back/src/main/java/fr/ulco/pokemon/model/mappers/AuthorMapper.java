package fr.ulco.pokemon.model.mappers;

import fr.ulco.pokemon.model.dto.out.AuthorDTO;
import fr.ulco.pokemon.model.entities.AuthorEntity;

public class AuthorMapper {
    
    public static AuthorDTO toDto(final AuthorEntity entity) {
        return new AuthorDTO(entity.getId(), entity.getName());
    }
    
}
