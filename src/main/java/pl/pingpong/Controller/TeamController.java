package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Team;
import pl.pingpong.service.TeamService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/get/list")
    public String getAllTeams(Model model) {
        List<Team> teamsList = teamService.getAllTeams();
        model.addAttribute("teamsList", teamsList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getTeamById(@PathVariable Integer id, Model model) {
        Optional<Team> team = teamService.getTeamById(id);
        if (team.isPresent()) {
            model.addAttribute("team", team);
            return ""; // WIDOK Team
        }
        return ""; // SZUKANY Team NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("team", new Team());
        return ""; // FORM DODAWANIA Team
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Team team, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD Team
        }
       teamService.saveTeam(team);
        return ""; //FORM ADD Team
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Team> team = teamService.getTeamById(id);
        if (team.isPresent()) {
            model.addAttribute("team", team);
            return ""; //REDIRECT DO TeamLIST
        }
        return ""; // SZUKANY Team NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Team team, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        teamService.saveTeam(team);
        return ""; // TeamLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteTeam(@PathVariable Integer id, Model model) {
        model.addAttribute("teamId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteTeamConfirmed(@PathVariable Integer id) {
        Optional<Team> team = teamService.getTeamById(id);

        if (team.isPresent()) {
            teamService.deleteTeam(id);
            return ""; // TeamLIST
        } else {
            return ""; // SZUKANY Team NIE ISTNIEJE
        }
    }
}