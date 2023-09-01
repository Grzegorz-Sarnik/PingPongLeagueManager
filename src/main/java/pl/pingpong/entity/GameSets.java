package pl.pingpong.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
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