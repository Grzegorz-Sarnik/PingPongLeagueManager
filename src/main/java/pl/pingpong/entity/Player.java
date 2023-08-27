package pl.pingpong.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import pl.pingpong.Person;

import javax.persistence.*;

@Entity
@Component
@Data
@ToString
@Table(name = "players")
public class Player extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String licenseNumber, role;
}
