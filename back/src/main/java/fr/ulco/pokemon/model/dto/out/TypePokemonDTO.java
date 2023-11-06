package fr.ulco.pokemon.model.dto.out;

import java.util.Collection;

public record TypePokemonDTO(long id, String name, Collection<PokemonDTO> pokemons) {
}
