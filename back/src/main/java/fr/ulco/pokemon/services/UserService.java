package fr.ulco.pokemon.services;

import fr.ulco.pokemon.model.dto.in.NewUserDTO;
import fr.ulco.pokemon.model.dto.out.UserDTO;

import java.net.URI;
import java.util.Optional;

public interface UserService {
    Optional<URI> createUser(NewUserDTO user);

    Boolean login(UserDTO newUser);
}
