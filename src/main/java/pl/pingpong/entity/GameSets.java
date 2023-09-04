package pl.pingpong.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "game_sets")
public class GameSets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(5)
    private int setNumber;

    @Min(0)
    @Max(11)
    private int scoreHome;
    @Min(0)
    @Max(11)
    private int scoreGuest;

    @ManyToOne
    @JoinColumn(name = "games_id")
    private Game game;
}