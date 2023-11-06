package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.TypeNotFoundException;
import fr.ulco.pokemon.model.dao.TypeRepository;
import fr.ulco.pokemon.model.dto.in.NewTypeDTO;
import fr.ulco.pokemon.model.dto.out.TypePokemonDTO;
import fr.ulco.pokemon.model.entities.TypeEntity;
import fr.ulco.pokemon.model.mappers.TypeMapper;
import fr.ulco.pokemon.services.TypeService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLTypeService implements TypeService {

    private final TypeRepository typeRepository;

    @Override
    public Optional<TypePokemonDTO> findById(Long id) {
        return typeRepository.findById(id).map(TypeMapper::toDto);
    }

    @Override
    public Either<TypeNotFoundException, TypePokemonDTO> findByName(String name) {
        return typeRepository.findByName(name)
                .toEither(new TypeNotFoundException())
                .map(TypeMapper::toDto);
    }

    @Override
    public Collection<String> findNames() {
        return typeRepository.findAll().stream().map(TypeEntity::getName).toList();
    }

    @Override
    public Optional<URI> createType(NewTypeDTO newType) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(newType.name());
        var saved = typeRepository.save(typeEntity);
        return Optional.of(URI.create("/types/" + saved.getId()));
    }
}
