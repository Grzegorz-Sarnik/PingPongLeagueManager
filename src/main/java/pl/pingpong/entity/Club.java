package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "club")
    private List<Team> teams;

    @OneToOne
    private Manager manager;

    @OneToMany
    private List<Player> players;
}
