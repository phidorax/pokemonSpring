package fr.ulco.pokemon.configurations;

import fr.ulco.pokemon.model.dao.AbilityRepository;
import fr.ulco.pokemon.model.dao.MoveRepository;
import fr.ulco.pokemon.model.dao.PokemonRepository;
import fr.ulco.pokemon.model.dao.TypeRepository;
import fr.ulco.pokemon.services.*;
import fr.ulco.pokemon.services.sql.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ServicesConfig {
    @Bean
    public PokemonService pokemonService(final PokemonRepository pokemonRepository, final TypeRepository typeRepository) {
        return new SQLPokemonService(pokemonRepository, typeRepository);
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

    @Bean
    public UserService userService(final JdbcUserDetailsManager userDetailsManager) {
        return new SQLUserService(userDetailsManager);
    }
}
