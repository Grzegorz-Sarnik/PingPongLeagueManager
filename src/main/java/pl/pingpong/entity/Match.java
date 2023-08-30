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
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "leagues_matchdays_id")
    private LeagueMatchday leagueMatchday;

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team guestTeam;

    private Integer resultHomeTeam;
    private Integer resultGuestTeam;

    @OneToMany(mappedBy = "match")
    private List<Game> games;
}
