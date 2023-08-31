package pl.pingpong.service;

import org.springframework.stereotype.Service;
import pl.pingpong.entity.GameSets;
import pl.pingpong.repository.GameSetsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GameSetsService {

    private GameSetsRepository gameSetsRepository;

    public GameSetsService(GameSetsRepository gameSetsRepository) {
        this.gameSetsRepository = gameSetsRepository;
    }

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
