package pl.pingpong.service;

import org.springframework.stereotype.Service;
import pl.pingpong.entity.League;
import pl.pingpong.repository.LeagueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {

    private LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public List<League> getAllLeagues() {
        return (List<League>) leagueRepository.findAll();
    }

    public Optional<League> getLeagueById(Integer id) {
        return leagueRepository.findById(id);
    }

    public League saveLeague(League league) {
        return leagueRepository.save(league);
    }

    public void deleteLeague(Integer id) {
        leagueRepository.deleteById(id);
    }
}
