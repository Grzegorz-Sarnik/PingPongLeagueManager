package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Match;

public interface MatchRepository extends CrudRepository<Match, Integer> {
}