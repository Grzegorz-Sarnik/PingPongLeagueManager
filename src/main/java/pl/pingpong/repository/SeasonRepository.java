package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Season;

public interface SeasonRepository extends CrudRepository<Season, Integer> {
}