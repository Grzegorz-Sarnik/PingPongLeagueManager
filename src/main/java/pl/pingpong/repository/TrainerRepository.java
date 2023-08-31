package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Trainer;

public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
}