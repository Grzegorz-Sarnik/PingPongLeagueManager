package pl.pingpong.service;

import org.springframework.stereotype.Service;
import pl.pingpong.entity.LeagueMatchday;
import pl.pingpong.repository.LeagueMatchdayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueMatchDayService {

    private LeagueMatchdayRepository leagueMatchDayRepository;

    public LeagueMatchDayService(LeagueMatchdayRepository leagueMatchDayRepository) {
        this.leagueMatchDayRepository = leagueMatchDayRepository;
    }

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
