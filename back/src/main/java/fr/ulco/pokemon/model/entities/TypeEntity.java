package fr.ulco.pokemon.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Table(name = "type")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "types")
    private Collection<PokemonEntity> pokemons;

    @ManyToOne
    private MoveEntity move;
}
