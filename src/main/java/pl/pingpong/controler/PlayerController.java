package pl.pingpong.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.dao.PlayerDao;
import pl.pingpong.entity.Player;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    private PlayerDao playerDao;

    public PlayerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<Player> players = playerDao.findAll();
        model.addAttribute("players", players);
        return "/player/list-player";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("player", new Player());
        return "/player/add-player-form";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Player player, BindingResult result) {
        if (result.hasErrors()) return "/player/add-player-form";
        playerDao.addPlayer(player);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePlayer(@PathVariable int id, Model model) {
        Player player = playerDao.findById(id);
        model.addAttribute("player", player);
        return "/player/update-player-form";
    }

    @PostMapping("/update/{id}")
    public String processUpdatePlayer(@Valid Player player, BindingResult result) {
        if (result.hasErrors())
            return "/player/update-player-form";
        playerDao.updatePlayer(player);
        return "redirect:/list";
    }

    @GetMapping("/remove/{id}")
    public String removePlayer(@PathVariable int id, Model model) {
        model.addAttribute("playerId", id);
        return "/player/remove-player-form";
    }

    @PostMapping("remove/{id}/confirmed")
    public String removePlayerConfirm(@PathVariable int id) {
        Player player = playerDao.findById(id);
        playerDao.removePlayer(player);
        return "redirect:/list";
    }
}