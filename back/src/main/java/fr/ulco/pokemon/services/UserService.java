package fr.ulco.pokemon.services;

import fr.ulco.pokemon.model.dto.in.NewUserDTO;

import java.net.URI;
import java.util.Optional;

public interface UserService {
    Optional<URI> createUser(NewUserDTO user);
}
