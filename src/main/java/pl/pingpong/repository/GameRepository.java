package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Game;

public interface GameRepository extends CrudRepository<Game, Integer> {
}