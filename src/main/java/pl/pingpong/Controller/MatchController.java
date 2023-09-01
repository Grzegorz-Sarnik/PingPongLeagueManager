package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Match;
import pl.pingpong.service.MatchService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/get/list")
    public String getAllMatchs(Model model) {
        List<Match> matchesList = matchService.getAllMatches();
        model.addAttribute("matchsList", matchesList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getMatchById(@PathVariable Integer id, Model model) {
        Optional<Match> match = matchService.getMatchById(id);
        if (match.isPresent()) {
            model.addAttribute("match", match);
            return ""; // WIDOK Match
        }
        return ""; // SZUKANY Match NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("match", new Match());
        return ""; // FORM DODAWANIA Match
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Match match, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD Match
        }
        matchService.saveMatch(match);
        return ""; //FORM ADD Match
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Match> match = matchService.getMatchById(id);
        if (match.isPresent()) {
            model.addAttribute("match", match);
            return ""; //REDIRECT DO MatchLIST
        }
        return ""; // SZUKANY Match NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Match match, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        matchService.saveMatch(match);
        return ""; // MatchLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteMatch(@PathVariable Integer id, Model model) {
        model.addAttribute("matchId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteMatchConfirmed(@PathVariable Integer id) {
        Optional<Match> match = matchService.getMatchById(id);

        if (match.isPresent()) {
            matchService.deleteMatch(id);
            return ""; // MatchLIST
        } else {
            return ""; // SZUKANY Match NIE ISTNIEJE
        }
    }
}