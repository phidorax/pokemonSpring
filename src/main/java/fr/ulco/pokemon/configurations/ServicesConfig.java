package fr.ulco.pokemon.configurations;

import fr.ulco.pokemon.model.dao.AuthorRepository;
import fr.ulco.pokemon.services.AuthorService;
import fr.ulco.pokemon.services.sql.SQLAuthorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public AuthorService authorService(final AuthorRepository authorRepository) {
        return new SQLAuthorService(authorRepository);
    }
}
