package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.Match;
import pl.pingpong.repository.MatchRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public List<Match> getAllMatches() {
        return (List<Match>) matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public void deleteMatch(Integer id) {
        matchRepository.deleteById(id);
    }
}
