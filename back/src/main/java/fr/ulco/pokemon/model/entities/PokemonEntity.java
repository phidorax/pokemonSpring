package fr.ulco.pokemon.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Table(name = "pokemon")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class PokemonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

    @Column(name = "special_attack")
    private Integer specialAttack;

    @Column(name = "special_defense")
    private Integer specialDefense;

    @Column(name = "speed")
    private Integer speed;

    @ManyToMany
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id")
    )

    private Collection<TypeEntity> types;
}