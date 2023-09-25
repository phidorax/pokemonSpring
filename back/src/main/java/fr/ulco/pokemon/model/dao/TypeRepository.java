package fr.ulco.pokemon.model.dao;

import fr.ulco.pokemon.model.entities.TypeEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    Option<TypeEntity> findByName(final String name);
}
