package fr.ulco.pokemon.model.dto.in;

import fr.ulco.pokemon.model.dto.out.TypeDTO;

import java.util.Collection;

public record NewPokemonDTO(String name, Integer hp, Integer attack, Integer defense, Integer specialAttack,
                            Integer specialDefense, Integer speed, Collection<TypeDTO> types) {}
