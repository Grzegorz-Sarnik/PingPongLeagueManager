package pl.pingpong.service;

import org.springframework.stereotype.Service;
import pl.pingpong.entity.Game;
import pl.pingpong.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(Integer id) {
        gameRepository.deleteById(id);
    }
}
