package fr.ulco.pokemon.model.dto.in;

public record NewPokemonDTO(String name, Integer hp, Integer attack, Integer defense, Integer specialAttack,
                            Integer specialDefense, Integer speed) {}
