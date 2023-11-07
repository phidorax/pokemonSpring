package fr.ulco.pokemon.services;

import fr.ulco.pokemon.exceptions.AbilityNotFoundException;
import fr.ulco.pokemon.model.dto.in.NewAbilityDTO;
import fr.ulco.pokemon.model.dto.out.AbilityDTO;
import io.vavr.control.Either;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

public interface AbilityService {
    Optional<AbilityDTO> findById(final Long id);

    Either<AbilityNotFoundException, AbilityDTO> findByName(final String name);

    Collection<String> findNames();

    Optional<URI> createAbility(final NewAbilityDTO newAbility);

    Optional<URI> editAbility(Long id, final NewAbilityDTO newAbility);

    Boolean deleteAbility(Long id);
}
