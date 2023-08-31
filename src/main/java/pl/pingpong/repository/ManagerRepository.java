package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
}