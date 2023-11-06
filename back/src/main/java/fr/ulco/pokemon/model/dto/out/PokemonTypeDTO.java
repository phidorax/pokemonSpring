package fr.ulco.pokemon.model.dto.out;

import java.util.Collection;

public record PokemonTypeDTO(Long id, String name, Integer hp, Integer attack, Integer defense, Integer specialAttack,
                             Integer specialDefense, Integer speed, Collection<TypeDTO> types) {
}
