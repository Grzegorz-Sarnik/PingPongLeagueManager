package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.LeagueMatchday;

public interface LeagueMatchdayRepository extends CrudRepository<LeagueMatchday, Integer> {
}