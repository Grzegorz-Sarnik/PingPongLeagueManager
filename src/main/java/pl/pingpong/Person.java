package pl.pingpong;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String firstName;
    public String lastName;
    public String gender;
    public LocalDate dateOfBirth;
    public String nationality;

}
