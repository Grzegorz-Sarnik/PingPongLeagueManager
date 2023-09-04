package pl.pingpong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.Club;

public interface ClubRepository extends CrudRepository<Club, Integer> {

  @Query("SELECT c FROM Club c WHERE c.name =?1")
    Club findByName(String name);

}