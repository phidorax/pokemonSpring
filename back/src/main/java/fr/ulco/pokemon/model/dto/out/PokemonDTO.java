package fr.ulco.pokemon.model.dto.out;

import jakarta.persistence.Column;

public record PokemonDTO(Long id, String name, Integer hp, Integer attack, Integer defense, Integer specialAttack,
                         Integer specialDefense, Integer speed) {
}
