package fr.ulco.pokemon.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Table(name = "move")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class MoveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "power")
    private Integer power;

    @Column(name = "accuracy")
    private Integer accuracy;

    @Column(name = "pp")
    private Integer pp;

    @OneToMany
    private Collection<TypeEntity> type;

    @Column(name = "description")
    private String description;
}
