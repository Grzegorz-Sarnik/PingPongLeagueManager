package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.Season;
import pl.pingpong.repository.SeasonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public List<Season> getAllSeasons() {
        return (List<Season>) seasonRepository.findAll();
    }

    public Optional<Season> getSeasonById(Integer id) {
        return seasonRepository.findById(id);
    }

    public Season saveSeason(Season season) {
        return seasonRepository.save(season);
    }

    public void deleteSeason(Integer id) {
        seasonRepository.deleteById(id);
    }
}
