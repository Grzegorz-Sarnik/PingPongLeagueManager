package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}