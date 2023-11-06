package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.TypeNotFoundException;
import fr.ulco.pokemon.model.dao.TypeRepository;
import fr.ulco.pokemon.model.dto.out.TypePokemonDTO;
import fr.ulco.pokemon.model.entities.TypeEntity;
import fr.ulco.pokemon.model.mappers.TypeMapper;
import fr.ulco.pokemon.services.TypeService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLTypeService implements TypeService {

    private final TypeRepository TypeRepository;

    @Override
    public Optional<TypePokemonDTO> findById(Long id) {
        return TypeRepository.findById(id).map(TypeMapper::toDto);
    }

    @Override
    public Either<TypeNotFoundException, TypePokemonDTO> findByName(String name) {
        return TypeRepository.findByName(name)
                .toEither(new TypeNotFoundException())
                .map(TypeMapper::toDto);
    }

    @Override
    public Collection<String> findNames() {
        return TypeRepository.findAll().stream().map(TypeEntity::getName).toList();
    }
}
