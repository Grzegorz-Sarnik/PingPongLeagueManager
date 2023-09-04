package pl.pingpong.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private PersonData personData;

    @NotBlank
    private String password;
    @NotBlank
    private String login;

    private String userRole;

    public User(){
        this.userRole = "user";
    }
}
