package fr.ulco.pokemon.services.sql;

import fr.ulco.pokemon.model.dto.in.NewUserDTO;
import fr.ulco.pokemon.model.dto.out.UserDTO;
import fr.ulco.pokemon.services.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.net.URI;
import java.util.Optional;

public class SQLUserService implements UserService {
    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public SQLUserService(JdbcUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @Override
    public Optional<URI> createUser(NewUserDTO user) {
        UserDetails jdbcUser = User.builder()
                .username(user.username())
                .password(passwordEncoder.encode(user.password()))
                .roles("USER")
                .build();
        if (!userDetailsManager.userExists(jdbcUser.getUsername())) {
            userDetailsManager.createUser(jdbcUser);
            return Optional.of(URI.create("/users/" + jdbcUser.getUsername()));
        }
        return Optional.of(URI.create("/"));
    }

    @Override
    public Boolean login(UserDTO user) {
        return passwordEncoder.matches(user.password(), userDetailsManager.loadUserByUsername(user.username()).getPassword());
    }
}