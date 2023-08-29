package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.EnumMap;
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
    private Team  homeTeam;

    @OneToOne
    private Team guestTeam;

    private String resultHomeTeam;
    private String resultGuestTeam;

    @OneToMany(mappedBy = "match")
    private List <Game> games;



}