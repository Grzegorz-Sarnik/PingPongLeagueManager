package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Component
@Data
@ToString
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String contact;

    @NotNull
    @NotBlank
    private String address;

    @OneToMany(mappedBy = "club")
    private List<Team> teams;

    @OneToOne
    private Manager manager;

    @OneToMany
    private List<Player> players;
}
