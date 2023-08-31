package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}