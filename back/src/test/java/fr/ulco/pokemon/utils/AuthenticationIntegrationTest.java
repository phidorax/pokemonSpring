package fr.ulco.pokemon.utils;

import fr.ulco.pokemon.configurations.DatabaseConfig;
import fr.ulco.pokemon.controllers.AuthUtils;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DatabaseConfig.class})
@RunWith(SpringRunner.class)
public class AuthenticationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    /**
     * You need to launch your server before launching this test.
     * curl -v localhost:8080/authors -u admin:admin
     */
    @Test
    public void testAuthentication() throws Exception {
        final var basicPayload = Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
        final var rest = new RestTemplateBuilder()
                .defaultHeader("Authorization", "Basic " + basicPayload)
                .build();

        final var postRequest = MockMvcRequestBuilders.post("/pokemons/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"attack\": 1000, \"defense\": 1000, \"hp\": 1000, \"name\": \"Mewthree\", \"specialAttack\": 1000, \"specialDefense\": 1000, \"speed\": 1000, \"types\": []}")
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        // Get id from header
        final String location = mvc.perform(postRequest)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn()
                .getResponse()
                .getHeader("Location");

        assert location != null;

        assert location.startsWith("/pokemons/");

        // Get id in the location
        final String id = location.substring(location.lastIndexOf("/") + 1);


        // Now perform a GET request to verify the Pokémon was created correctly
        final var getRequest = MockMvcRequestBuilders.get(location)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        mvc.perform(getRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{\"id\":"+id+",\"name\":\"Mewthree\",\"hp\":1000,\"attack\":1000,\"defense\":1000,\"specialAttack\":1000,\"specialDefense\":1000,\"speed\":1000}"));
    }
}
