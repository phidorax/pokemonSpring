package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.exceptions.MoveNotFoundException;
import fr.ulco.pokemon.model.dao.MoveRepository;
import fr.ulco.pokemon.model.dto.in.NewMoveDTO;
import fr.ulco.pokemon.model.dto.out.MoveDTO;
import fr.ulco.pokemon.model.entities.MoveEntity;
import fr.ulco.pokemon.model.mappers.MoveMapper;
import fr.ulco.pokemon.services.MoveService;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.net.URI;
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

    @Override
    public Optional<URI> createMove(NewMoveDTO newMove) {
        MoveEntity moveEntity = new MoveEntity();
        return fillMoveEntity(newMove, moveEntity);
    }

    @Override
    public Optional<URI> editMove(Long id, NewMoveDTO newMove) {
        Optional<MoveEntity> moveById = MoveRepository.findById(id);
        if (moveById.isPresent()) {
            MoveEntity moveEntity = moveById.get();
            return fillMoveEntity(newMove, moveEntity);
        } else {
            return Optional.empty();
        }
    }

    private Optional<URI> fillMoveEntity(NewMoveDTO newMove, MoveEntity moveEntity) {
        moveEntity.setName(newMove.name());
        moveEntity.setDescription(newMove.description());
        moveEntity.setPower(newMove.power());
        moveEntity.setAccuracy(newMove.accuracy());
        moveEntity.setPp(newMove.pp());
        var saved = MoveRepository.save(moveEntity);
        return Optional.of(URI.create("/moves/" + saved.getId()));
    }

    @Override
    public Boolean deleteMove(Long id) {
        Optional<MoveEntity> moveById = MoveRepository.findById(id);
        if (moveById.isPresent()) {
            MoveRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
