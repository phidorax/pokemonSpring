package fr.ulco.pokemon.controllers;

import fr.ulco.pokemon.model.dao.PokemonRepository;
import fr.ulco.pokemon.model.entities.PokemonEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonController controller;

    @Test
    public void shouldFindPokemons() throws Exception {
        final var pikachu = new PokemonEntity();
        pikachu.setName("Pikachu");
        pikachu.setHp(35);
        pikachu.setAttack(55);
        pikachu.setDefense(40);
        pikachu.setSpeed(90);
        pikachu.setSpecialAttack(50);
        pikachu.setSpecialDefense(50);

        final var charmander = new PokemonEntity();
        charmander.setName("Charmander");
        charmander.setHp(39);
        charmander.setAttack(52);
        charmander.setDefense(43);
        charmander.setSpeed(65);
        charmander.setSpecialAttack(60);
        charmander.setSpecialDefense(50);

        when(pokemonRepository.findAll()).thenReturn(Arrays.asList(pikachu, charmander));

        final var request = MockMvcRequestBuilders.get("/pokemons")
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        mvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("[\"Pikachu\", \"Charmander\"]"));
    }

    @Test
    public void shouldFindPokemonById() throws Exception {
        final var pikachu = new PokemonEntity();
        pikachu.setName("Pikachu");
        pikachu.setHp(35);
        pikachu.setAttack(55);
        pikachu.setDefense(40);
        pikachu.setSpeed(90);
        pikachu.setSpecialAttack(50);
        pikachu.setSpecialDefense(50);

        final var charmander = new PokemonEntity();
        charmander.setName("Charmander");
        charmander.setHp(39);
        charmander.setAttack(52);
        charmander.setDefense(43);
        charmander.setSpeed(65);
        charmander.setSpecialAttack(60);
        charmander.setSpecialDefense(50);

        when(pokemonRepository.findById(1L))
                .thenReturn(java.util.Optional.of(pikachu));

        when(pokemonRepository.findById(2L))
                .thenReturn(java.util.Optional.of(charmander));

        final var request1 = MockMvcRequestBuilders.get("/pokemons/1")
                .header("Authorization", "Basic " + AuthUtils.basicPayload);
        mvc.perform(request1)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"name\": \"Pikachu\", \"hp\": 35, \"attack\": 55, \"defense\": 40, \"specialAttack\": 50, \"specialDefense\": 50, \"speed\": 90}"));

        final var request2 = MockMvcRequestBuilders.get("/pokemons/2")
                .header("Authorization", "Basic " + AuthUtils.basicPayload);
        mvc.perform(request2)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"name\": \"Charmander\", \"hp\": 39, \"attack\": 52, \"defense\": 43, \"specialAttack\": 60, \"specialDefense\": 50, \"speed\": 65}"));
    }
}