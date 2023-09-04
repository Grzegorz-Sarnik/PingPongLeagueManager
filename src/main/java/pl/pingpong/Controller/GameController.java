package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Game;
import pl.pingpong.service.GameService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @GetMapping("/get/list")
    public String getAllGames(Model model) {
        List<Game> gamesList = gameService.getAllGames();
        model.addAttribute("gamesList", gamesList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getGameById(@PathVariable Integer id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            model.addAttribute("game", game);
            return ""; // WIDOK Game
        }
        return ""; // SZUKANY Game NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());
        return ""; // FORM DODAWANIA Game
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Game game, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD Game
        }
        gameService.saveGame(game);
        return ""; //FORM ADD Game
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            model.addAttribute("Game", game);
            return ""; //REDIRECT DO GameLIST
        }
        return ""; // SZUKANY Game NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Game game, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        gameService.saveGame(game);
        return ""; // GameLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Integer id, Model model) {
        model.addAttribute("GameId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteGameConfirmed(@PathVariable Integer id) {
        Optional<Game> game = gameService.getGameById(id);

        if (game.isPresent()) {
            gameService.deleteGame(id);
            return ""; // GameLIST
        } else {
            return ""; // SZUKANY Game NIE ISTNIEJE
        }
    }
}