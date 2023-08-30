package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Component
@Data
@ToString
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int gameNumber;

    @ManyToOne
    @JoinColumn(name = "matches_id")
    private Match match;

    @OneToOne
    private Player homePlayer;

    @OneToOne
    private Player guestPlayer;
    @OneToOne
    private Player winner;
    @OneToOne
    private Player looser;

    private int resultHome;
    private int resultGuestTeam;

    @OneToMany(mappedBy = "game")
    private List<GameSets> gameSets;
}
