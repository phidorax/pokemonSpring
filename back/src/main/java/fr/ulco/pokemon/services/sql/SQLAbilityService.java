package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.AbilityNotFoundException;
import fr.ulco.pokemon.model.dao.AbilityRepository;
import fr.ulco.pokemon.model.dto.in.NewAbilityDTO;
import fr.ulco.pokemon.model.dto.out.AbilityDTO;
import fr.ulco.pokemon.model.entities.AbilityEntity;
import fr.ulco.pokemon.model.mappers.AbilityMapper;
import fr.ulco.pokemon.services.AbilityService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLAbilityService implements AbilityService {

    private final AbilityRepository abilityRepository;

    @Override
    public Optional<AbilityDTO> findById(Long id) {
        return abilityRepository.findById(id).map(AbilityMapper::toDto);
    }

    @Override
    public Either<AbilityNotFoundException, AbilityDTO> findByName(String name) {
        return abilityRepository.findByName(name)
                .toEither(new AbilityNotFoundException())
                .map(AbilityMapper::toDto);
    }

    @Override
    public Collection<String> findNames() {
        return abilityRepository.findAll().stream().map(AbilityEntity::getName).toList();
    }

    @Override
    public Optional<URI> createAbility(NewAbilityDTO newAbility) {
        AbilityEntity abilityEntity = new AbilityEntity();
        abilityEntity.setName(newAbility.name());
        abilityEntity.setDescription(newAbility.description());
        var saved = abilityRepository.save(abilityEntity);
        return Optional.of(URI.create("/abilities/" + saved.getId()));
    }

    @Override
    public Optional<URI> editAbility(Long id, NewAbilityDTO newAbility) {
        Optional<AbilityEntity> abilityById = abilityRepository.findById(id);
        if (abilityById.isPresent()) {
            AbilityEntity abilityEntity = abilityById.get();
            abilityEntity.setName(newAbility.name());
            abilityEntity.setDescription(newAbility.description());
            var saved = abilityRepository.save(abilityEntity);
            return Optional.of(URI.create("/abilities/" + saved.getId()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteAbility(Long id) {
        Optional<AbilityEntity> abilityById = abilityRepository.findById(id);
        if (abilityById.isPresent()) {
            abilityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
