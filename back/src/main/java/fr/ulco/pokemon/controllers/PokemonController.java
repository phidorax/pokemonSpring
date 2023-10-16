package fr.ulco.pokemon.controllers;

import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("Pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping(Routes.GET_POKEMONS)
    public ResponseEntity<Collection<String>> getPokemons() {
        return ResponseEntity.ok(pokemonService.findNames());
    }

    @GetMapping(Routes.GET_POKEMONS_DETAILS)
    public ResponseEntity<PokemonDTO> getPokemonDetails(@PathVariable("id") final Long id) {
        return pokemonService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(Routes.POST_POKEMONS)
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody final NewPokemonDTO newPokemon) {
        return pokemonService.createPokemon(newPokemon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
