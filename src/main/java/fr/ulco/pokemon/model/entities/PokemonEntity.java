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
    private int hp;

    @Column(name = "attack")
    private int attack;

    @Column(name = "defense")
    private int defense;

    @Column(name = "special_attack")
    private int specialAttack;

    @Column(name = "special_defense")
    private int specialDefense;

    @Column(name = "speed")
    private int speed;

    @ManyToMany
    @JoinTable(
            name = "pokemon_type",
            joinColumns = @JoinColumn(name = "pokemon_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id")
    )

    @OneToMany(mappedBy = "type")
    private Collection<TypeEntity> type1;

    @OneToMany(mappedBy = "type")
    private Collection<TypeEntity> type2;
}
