package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.GameSets;
import pl.pingpong.service.GameSetsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gameSets")
public class GameSetsController {

    private final GameSetsService gameSetsService;

    @GetMapping("/get/list")
    public String getAllGameSetss(Model model) {
        List<GameSets> gameSetssList = gameSetsService.getAllGameSets();
        model.addAttribute("gameSetsList", gameSetssList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getGameSetsById(@PathVariable Integer id, Model model) {
        Optional<GameSets> gameSets = gameSetsService.getGameSetsById(id);
        if (gameSets.isPresent()) {
            model.addAttribute("gameSets", gameSets);
            return ""; // WIDOK GameSets
        }
        return ""; // SZUKANY GameSets NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("gameSets", new GameSets());
        return ""; // FORM DODAWANIA GameSets
    }

    @PostMapping("/add")
    public String processAddForm(@Valid GameSets gameSets, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD GameSets
        }
        gameSetsService.saveGameSets(gameSets);
        return ""; //FORM ADD GameSets
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<GameSets> gameSets = gameSetsService.getGameSetsById(id);
        if (gameSets.isPresent()) {
            model.addAttribute("gameSets", gameSets);
            return ""; //REDIRECT DO GameSetsLIST
        }
        return ""; // SZUKANY GameSets NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid GameSets gameSets, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        gameSetsService.saveGameSets(gameSets);
        return ""; // GameSetsLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteGameSets(@PathVariable Integer id, Model model) {
        model.addAttribute("gameSetsId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteGameSetsConfirmed(@PathVariable Integer id) {
        Optional<GameSets> gameSets = gameSetsService.getGameSetsById(id);

        if (gameSets.isPresent()) {
            gameSetsService.deleteGameSets(id);
            return ""; // GameSetsLIST
        } else {
            return ""; // SZUKANY GameSets NIE ISTNIEJE
        }
    }
}