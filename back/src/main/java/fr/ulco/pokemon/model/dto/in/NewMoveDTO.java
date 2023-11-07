package fr.ulco.pokemon.model.dto.in;

public record NewMoveDTO(String name, String description, Integer power, Integer accuracy, Integer pp) {
}
