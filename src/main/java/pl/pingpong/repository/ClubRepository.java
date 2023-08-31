package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Club;

public interface ClubRepository extends CrudRepository<Club, Integer> {

}