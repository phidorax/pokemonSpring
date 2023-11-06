package fr.ulco.pokemon.model.dto.out;

public record PokemonDTO(Long id, String name, Integer hp, Integer attack, Integer defense, Integer specialAttack,
                         Integer specialDefense, Integer speed) {
}
