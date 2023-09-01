package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.LeagueMatchday;
import pl.pingpong.repository.LeagueMatchdayRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeagueMatchDayService {

    private final LeagueMatchdayRepository leagueMatchDayRepository;

    public List<LeagueMatchday> getAllLeagueMatchDays() {
        return (List<LeagueMatchday>) leagueMatchDayRepository.findAll();
    }

    public Optional<LeagueMatchday> getLeagueMatchDayById(Integer id) {
        return leagueMatchDayRepository.findById(id);
    }

    public LeagueMatchday saveLeagueMatchDay(LeagueMatchday leagueMatchDay) {
        return leagueMatchDayRepository.save(leagueMatchDay);
    }

    public void deleteLeagueMatchDay(Integer id) {
        leagueMatchDayRepository.deleteById(id);
    }
}
