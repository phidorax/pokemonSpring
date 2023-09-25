package fr.ulco.pokemon.model.dao;

import fr.ulco.pokemon.model.entities.MoveEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity, Long> {
        Option<MoveEntity> findByName(final String name);
}
