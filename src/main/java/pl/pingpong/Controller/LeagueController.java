package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.League;
import pl.pingpong.service.LeagueService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController {

    private final LeagueService leagueService;

    @GetMapping("/get/list")
    public String getAllLeagues(Model model) {
        List<League> leaguesList = leagueService.getAllLeagues();
        model.addAttribute("leaguesList", leaguesList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getLeagueById(@PathVariable Integer id, Model model) {
        Optional<League> league = leagueService.getLeagueById(id);
        if (league.isPresent()) {
            model.addAttribute("league", league);
            return ""; // WIDOK League
        }
        return ""; // SZUKANY League NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("league", new League());
        return ""; // FORM DODAWANIA League
    }

    @PostMapping("/add")
    public String processAddForm(@Valid League league, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD League
        }
        leagueService.saveLeague(league);
        return ""; //FORM ADD League
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<League> league = leagueService.getLeagueById(id);
        if (league.isPresent()) {
            model.addAttribute("league", league);
            return ""; //REDIRECT DO LeagueLIST
        }
        return ""; // SZUKANY League NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid League league, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        leagueService.saveLeague(league);
        return ""; // LeagueLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteLeague(@PathVariable Integer id, Model model) {
        model.addAttribute("leagueId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteLeagueConfirmed(@PathVariable Integer id) {
        Optional<League> league = leagueService.getLeagueById(id);

        if (league.isPresent()) {
            leagueService.deleteLeague(id);
            return ""; // LeagueLIST
        } else {
            return ""; // SZUKANY League NIE ISTNIEJE
        }
    }
}