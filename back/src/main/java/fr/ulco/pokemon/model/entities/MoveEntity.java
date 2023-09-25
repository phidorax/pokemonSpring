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
    private int power;

    @Column(name = "accuracy")
    private int accuracy;

    @Column(name = "pp")
    private int pp;

    @OneToMany
    private Collection<TypeEntity> type;

    @Column(name = "description")
    private String description;


}
