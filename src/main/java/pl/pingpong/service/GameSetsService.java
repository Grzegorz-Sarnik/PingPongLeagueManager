package pl.pingpong.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pingpong.entity.GameSets;
import pl.pingpong.repository.GameSetsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameSetsService {

    private final GameSetsRepository gameSetsRepository;

    public List<GameSets> getAllGameSets() {
        return (List<GameSets>) gameSetsRepository.findAll();
    }

    public Optional<GameSets> getGameSetsById(Integer id) {
        return gameSetsRepository.findById(id);
    }

    public GameSets saveGameSets(GameSets gameSets) {
        return gameSetsRepository.save(gameSets);
    }

    public void deleteGameSets(Integer id) {
        gameSetsRepository.deleteById(id);
    }
}
