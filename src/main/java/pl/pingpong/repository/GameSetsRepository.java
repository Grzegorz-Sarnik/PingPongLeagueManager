package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.GameSets;

public interface GameSetsRepository extends CrudRepository<GameSets, Integer> {
}