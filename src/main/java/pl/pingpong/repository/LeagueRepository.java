package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.League;

public interface LeagueRepository extends CrudRepository<League, Integer> {
}