package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.PersonData;
import pl.pingpong.repository.PersonDataRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDataService {

    private final PersonDataRepository personDataRepository;

    public List<PersonData> getAllPersonData() {
        return (List<PersonData>) personDataRepository.findAll();
    }

    public Optional<PersonData> getPersonDataById(Integer id) {
        return personDataRepository.findById(id);
    }

    public PersonData savePersonData(PersonData PersonData) {
        return personDataRepository.save(PersonData);
    }

    public void deletePersonData(Integer id) {
        personDataRepository.deleteById(id);
    }
}
