package fr.ulco.pokemon.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "type")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PokemonEntity pokemon;

    @ManyToOne
    private MoveEntity move;
}
