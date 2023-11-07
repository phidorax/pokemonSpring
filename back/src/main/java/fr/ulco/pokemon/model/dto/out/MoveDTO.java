package fr.ulco.pokemon.model.dto.out;

public record MoveDTO(long id, String name, String description, int power, int accuracy, int pp) {
}
