package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "leagues_matchdays")
public class LeagueMatchday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "leagues_id")
    private League league;

    @OneToMany(mappedBy = "leagueMatchday")
    private List<Match> matches;
}
