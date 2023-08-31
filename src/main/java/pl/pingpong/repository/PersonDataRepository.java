package pl.pingpong.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pingpong.entity.PersonData;

public interface PersonDataRepository extends CrudRepository<PersonData, Long> {
}