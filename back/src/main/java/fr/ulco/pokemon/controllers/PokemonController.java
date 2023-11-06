package fr.ulco.pokemon.controllers;

import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import fr.ulco.pokemon.model.dto.out.PokemonDTO;
import fr.ulco.pokemon.services.PokemonService;
import fr.ulco.pokemon.services.AbilityService;
import fr.ulco.pokemon.services.TypeService;
import fr.ulco.pokemon.services.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("Pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;
    private final AbilityService abilityService;
    private final TypeService typeService;
    private final MoveService moveService;

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

    @PostMapping(Routes.CREATE_POKEMON)
    public ResponseEntity<Object> createPokemon(@RequestBody final NewPokemonDTO newPokemon) {
        return pokemonService.createPokemon(newPokemon)
                .map(uri -> ResponseEntity.created(uri).build())
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(Routes.GET_ABILITIES)
    public ResponseEntity<Collection<String>> getAbilities() {
        return ResponseEntity.ok(abilityService.findNames());
    }

    @GetMapping(Routes.GET_TYPES)
    public ResponseEntity<Collection<String>> getTypes() {
        return ResponseEntity.ok(typeService.findNames());
    }

    @GetMapping(Routes.GET_MOVES)
    public ResponseEntity<Collection<String>> getMoves() {
        return ResponseEntity.ok(moveService.findNames());
    }
}
