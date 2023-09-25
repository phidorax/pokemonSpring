package fr.ulco.pokemon.model.dao;

import fr.ulco.pokemon.model.entities.AbilityEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends JpaRepository<AbilityEntity, Long> {
    Option<AbilityEntity> findByName(final String name);
}
