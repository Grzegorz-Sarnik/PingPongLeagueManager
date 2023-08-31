package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}