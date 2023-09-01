package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.LeagueMatchday;
import pl.pingpong.repository.LeagueMatchdayRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeagueMatchdayService {

    private final LeagueMatchdayRepository LeagueMatchdayRepository;

    public List<LeagueMatchday> getAllLeagueMatchdays() {
        return (List<LeagueMatchday>) LeagueMatchdayRepository.findAll();
    }

    public Optional<LeagueMatchday> getLeagueMatchdayById(Integer id) {
        return LeagueMatchdayRepository.findById(id);
    }

    public LeagueMatchday saveLeagueMatchday(LeagueMatchday LeagueMatchday) {
        return LeagueMatchdayRepository.save(LeagueMatchday);
    }

    public void deleteLeagueMatchday(Integer id) {
        LeagueMatchdayRepository.deleteById(id);
    }
}
