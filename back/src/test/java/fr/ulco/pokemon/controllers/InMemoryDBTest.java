package fr.ulco.pokemon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ulco.pokemon.configurations.DatabaseConfig;
import fr.ulco.pokemon.model.dto.in.NewPokemonDTO;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DatabaseConfig.class})
@RunWith(SpringRunner.class)
public class InMemoryDBTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldCreatePokemonAndFindNewCreatedPokemon() throws Exception {
        final var content = new NewPokemonDTO(42L,"Bulbasaur", 45, 49, 49, 45, 65, 65, new ArrayList<>());

        final var request = MockMvcRequestBuilders.post(Routes.CREATE_POKEMON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(content))
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "/pokemons/42"));

        // Now perform a GET request to verify the Pokémon was created correctly
        final var getRequest = MockMvcRequestBuilders.get("/pokemons/42")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        mvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":42,\"name\":\"Bulbasaur\",\"hp\":45,\"attack\":49,\"defense\":49,\"specialAttack\":45,\"specialDefense\":65,\"speed\":65}"));
    }
}