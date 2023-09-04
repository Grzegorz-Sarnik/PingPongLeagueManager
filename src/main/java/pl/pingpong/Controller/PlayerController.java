package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.PersonData;
import pl.pingpong.entity.Player;
import pl.pingpong.service.PersonDataService;
import pl.pingpong.service.PlayerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final PersonDataService personDataService;

    @GetMapping("/get/list")
    public String getAllPlayers(Model model) {
        List<Player> playersList = playerService.getAllPlayers();
        model.addAttribute("playersList", playersList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getPlayerById(@PathVariable Integer id, Model model) {
        Optional<Player> player = playerService.getPlayerById(id);
        if (player.isPresent()) {
            model.addAttribute("player", player);
            return ""; // WIDOK Player
        }
        return ""; // SZUKANY Player NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("player", new Player());
        return "/player/add-player-form"; // FORM DODAWANIA Player
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Player player, BindingResult result) {
        if (result.hasErrors()) {
            return "/player/add-player-form"; // FORM ADD Player
        }
        PersonData personData = player.getPersonData();
        personDataService.savePersonData(personData);

        playerService.savePlayer(player);
        return "/player/add-player-form"; //FORM ADD Player
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Player> player = playerService.getPlayerById(id);
        if (player.isPresent()) {
            model.addAttribute("player", player);
            return ""; //REDIRECT DO PlayerLIST
        }
        return ""; // SZUKANY Player NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Player player, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        playerService.savePlayer(player);
        return ""; // PlayerLIST
    }

    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable Integer id, Model model) {
        model.addAttribute("playerId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deletePlayerConfirmed(@PathVariable Integer id) {
        Optional<Player> player = playerService.getPlayerById(id);

        if (player.isPresent()) {
            playerService.deletePlayer(id);
            return ""; // PlayerLIST
        } else {
            return ""; // SZUKANY Player NIE ISTNIEJE
        }
    }
}