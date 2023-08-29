package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import pl.pingpong.dao.PlayerDao;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> player;

    @ManyToOne
    private Club club;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

}
