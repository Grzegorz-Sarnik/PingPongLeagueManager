package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.rmi.dgc.Lease;
import java.time.Year;
import java.util.List;

@Entity
@Component
@Data
@ToString
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private int seasonNumber;

    @OneToMany
    @JoinColumn(name = "league_id")
    private List<League> league;


}
