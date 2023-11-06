package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.MoveNotFoundException;
import fr.ulco.pokemon.model.dao.MoveRepository;
import fr.ulco.pokemon.model.dto.out.MoveDTO;
import fr.ulco.pokemon.model.entities.MoveEntity;
import fr.ulco.pokemon.model.mappers.MoveMapper;
import fr.ulco.pokemon.services.MoveService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLMoveService implements MoveService {

    private final MoveRepository MoveRepository;

    @Override
    public Optional<MoveDTO> findById(Long id) {
        return MoveRepository.findById(id).map(MoveMapper::toDto);
    }

    @Override
    public Either<MoveNotFoundException, MoveDTO> findByName(String name) {
        return MoveRepository.findByName(name)
                .toEither(new MoveNotFoundException())
                .map(MoveMapper::toDto);
    }

    @Override
    public Collection<String> findNames() {
        return MoveRepository.findAll().stream().map(MoveEntity::getName).toList();
    }
}
