package pl.pingpong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "game_sets")
public class GameSets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int setNumber;

    private int scoreHome;

    private int scoreGuest;

    @ManyToOne
    @JoinColumn(name = "games_id")
    private Game game;
}