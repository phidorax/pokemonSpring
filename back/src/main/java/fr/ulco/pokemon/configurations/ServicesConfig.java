package fr.ulco.pokemon.configurations;

import fr.ulco.pokemon.model.dao.AbilityRepository;
import fr.ulco.pokemon.model.dao.MoveRepository;
import fr.ulco.pokemon.model.dao.PokemonRepository;
import fr.ulco.pokemon.model.dao.TypeRepository;
import fr.ulco.pokemon.services.AbilityService;
import fr.ulco.pokemon.services.MoveService;
import fr.ulco.pokemon.services.PokemonService;
import fr.ulco.pokemon.services.TypeService;
import fr.ulco.pokemon.services.sql.SQLAbilityService;
import fr.ulco.pokemon.services.sql.SQLMoveService;
import fr.ulco.pokemon.services.sql.SQLPokemonService;
import fr.ulco.pokemon.services.sql.SQLTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {
    @Bean
    public PokemonService pokemonService(final PokemonRepository pokemonRepository) {
        return new SQLPokemonService(pokemonRepository);
    }

    @Bean
    public AbilityService abilityService(final AbilityRepository abilityRepository) {
        return new SQLAbilityService(abilityRepository);
    }

    @Bean
    public TypeService typeService(final TypeRepository typeRepository) {
        return new SQLTypeService(typeRepository);
    }

    @Bean
    public MoveService moveService(final MoveRepository moveRepository) {
        return new SQLMoveService(moveRepository);
    }
}
