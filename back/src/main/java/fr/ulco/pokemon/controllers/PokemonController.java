package fr.ulco.pokemon.controllers;

import fr.ulco.pokemon.model.dto.in.*;
import fr.ulco.pokemon.model.dto.out.*;
import fr.ulco.pokemon.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static java.lang.Boolean.TRUE;
import static org.springframework.http.ResponseEntity.*;

@RestController("Pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;
    private final AbilityService abilityService;
    private final TypeService typeService;
    private final MoveService moveService;
    private final UserService userService;

    @GetMapping(Routes.GET_POKEMONS)
    public ResponseEntity<Collection<PokemonSimpleDTO>> getPokemons() {
        return ok(pokemonService.findNames());
    }

    @GetMapping(Routes.GET_POKEMONS_DETAILS)
    public ResponseEntity<PokemonTypeDTO> getPokemonDetails(@PathVariable("id") final Long id) {
        return pokemonService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @GetMapping(Routes.GET_POKEMONS_TYPES)
    public ResponseEntity<Collection<TypeDTO>> getPokemonTypes(@PathVariable("id") final Long id) {
        return pokemonService.findById(id)
                .map(PokemonTypeDTO::types)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PostMapping(Routes.CREATE_POKEMON)
    public ResponseEntity<Object> createPokemon(@RequestBody final NewPokemonDTO newPokemon) {
        return pokemonService.createPokemon(newPokemon)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @PostMapping(Routes.EDIT_POKEMON)
    public ResponseEntity<Object> editPokemon(@PathVariable("id") final Long id, @RequestBody final NewPokemonDTO newPokemon) {
        return pokemonService.editPokemon(id, newPokemon)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @DeleteMapping(Routes.DELETE_POKEMON)
    public ResponseEntity<Boolean> deletePokemon(@PathVariable("id") final Long id) {
        return pokemonService.deletePokemon(id) ? accepted().build() : noContent().build();
    }

    @GetMapping(Routes.GET_ABILITIES)
    public ResponseEntity<Collection<String>> getAbilities() {
        return ok(abilityService.findNames());
    }

    @GetMapping(Routes.GET_ABILITIES_DETAILS)
    public ResponseEntity<AbilityDTO> getAbilityDetails(@PathVariable("id") final Long id) {
        return abilityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PostMapping(Routes.CREATE_ABILITY)
    public ResponseEntity<Object> createAbility(@RequestBody final NewAbilityDTO newAbility) {
        return abilityService.createAbility(newAbility)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @PostMapping(Routes.EDIT_ABILITY)
    public ResponseEntity<Object> editAbility(@PathVariable("id") final Long id, @RequestBody final NewAbilityDTO newAbility) {
        return abilityService.editAbility(id, newAbility)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @DeleteMapping(Routes.DELETE_ABILITY)
    public ResponseEntity<Boolean> deleteAbility(@PathVariable("id") final Long id) {
        return abilityService.deleteAbility(id) ? accepted().build() : noContent().build();
    }

    @GetMapping(Routes.GET_TYPES)
    public ResponseEntity<Collection<String>> getTypes() {
        return ok(typeService.findNames());
    }

    @PostMapping(Routes.CREATE_TYPE)
    public ResponseEntity<Object> createType(@RequestBody final NewTypeDTO newType) {
        return typeService.createType(newType)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @PostMapping(Routes.EDIT_TYPE)
    public ResponseEntity<Object> editType(@PathVariable("id") final Long id, @RequestBody final NewTypeDTO newType) {
        return typeService.editType(id, newType)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @DeleteMapping(Routes.DELETE_TYPE)
    public ResponseEntity<Boolean> deleteType(@PathVariable("id") final Long id) {
        return typeService.deleteType(id) ? accepted().build() : noContent().build();
    }

    @GetMapping(Routes.GET_TYPES_DETAILS)
    public ResponseEntity<TypePokemonDTO> getTypeDetails(@PathVariable("id") final Long id) {
        return typeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @GetMapping(Routes.GET_MOVES)
    public ResponseEntity<Collection<String>> getMoves() {
        return ok(moveService.findNames());
    }

    @GetMapping(Routes.GET_MOVES_DETAILS)
    public ResponseEntity<MoveDTO> getMoveDetails(@PathVariable("id") final Long id) {
        return moveService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    @PostMapping(Routes.CREATE_MOVE)
    public ResponseEntity<Object> createMove(@RequestBody final NewMoveDTO newMove) {
        return moveService.createMove(newMove)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @PostMapping(Routes.EDIT_MOVE)
    public ResponseEntity<Object> editMove(@PathVariable("id") final Long id, @RequestBody final NewMoveDTO newMove) {
        return moveService.editMove(id, newMove)
                .map(uri -> created(uri).build())
                .orElseGet(() -> badRequest().build());
    }

    @DeleteMapping(Routes.DELETE_MOVE)
    public ResponseEntity<Boolean> deleteMove(@PathVariable("id") final Long id) {
        return moveService.deleteMove(id) ? accepted().build() : noContent().build();
    }


    @PostMapping(Routes.LOGIN)
    public ResponseEntity<Boolean> login(@RequestBody final UserDTO user) {
        return (userService.login(user)) ? ok(TRUE) : status(401).build();
    }

    @PostMapping(Routes.CREATE_USER)
    public ResponseEntity<Object> createUser(@RequestBody final NewUserDTO newUser) {
        return userService.createUser(newUser)
                .map(uri -> (uri.toString().contains("users")) ? created(uri).build() : status(409).build())
                .orElseGet(() -> badRequest().build());
    }
}
