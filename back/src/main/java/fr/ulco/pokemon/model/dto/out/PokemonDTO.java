package fr.ulco.pokemon.model.dto.out;

import java.util.List;

public record PokemonDTO(Long id, String name, Integer hp, Integer attack, Integer defense, Integer specialAttack,
                         Integer specialDefense, Integer speed, List<TypeDTO> types) {
}
