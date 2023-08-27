package pl.pingpong.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pingpong.dao.PlayerDao;
import pl.pingpong.entity.Player;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/player")
public class playerController {

    private PlayerDao playerDao;

    public playerController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @ResponseBody
    @GetMapping("/create")
    public String createPlayer(){

        Player player = new Player();
        player.setFirstName("Grzegorz");
        player.setLastName("Sarnik");
        player.setGender("Mężczyzna");
        player.setDateOfBirth(LocalDate.of(1991,1,21));
        player.setNationality("Polska");
        player.setLicenseNumber("163829");
        player.setRole("Zawodnik");
        playerDao.addPlayer(player);
        System.out.println(player);
        return "dodano";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<Player> players = playerDao.findAll();
        model.addAttribute("players", players);
        return "/player/player-list";
    }
}
