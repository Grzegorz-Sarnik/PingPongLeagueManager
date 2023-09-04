package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.LeagueMatchday;
import pl.pingpong.service.LeagueMatchdayService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/leagueMatchday")
public class LeagueMatchDayController {

    private final LeagueMatchdayService leagueMatchdayService;

    @GetMapping("/get/list")
    public String getAllLeagueMatchdays(Model model) {
        List<LeagueMatchday> leagueMatchdaysList = leagueMatchdayService.getAllLeagueMatchdays();
        model.addAttribute("leagueMatchdaysList", leagueMatchdaysList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getLeagueMatchdayById(@PathVariable Integer id, Model model) {
        Optional<LeagueMatchday> leagueMatchday = leagueMatchdayService.getLeagueMatchdayById(id);
        if (leagueMatchday.isPresent()) {
            model.addAttribute("leagueMatchday", leagueMatchday);
            return ""; // WIDOK LeagueMatchday
        }
        return ""; // SZUKANY LeagueMatchday NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("leagueMatchday", new LeagueMatchday());
        return ""; // FORM DODAWANIA LeagueMatchday
    }

    @PostMapping("/add")
    public String processAddForm(@Valid LeagueMatchday leagueMatchday, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD LeagueMatchday
        }
        leagueMatchdayService.saveLeagueMatchday(leagueMatchday);
        return ""; //FORM ADD LeagueMatchday
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<LeagueMatchday> leagueMatchday = leagueMatchdayService.getLeagueMatchdayById(id);
        if (leagueMatchday.isPresent()) {
            model.addAttribute("leagueMatchday", leagueMatchday);
            return ""; //REDIRECT DO LeagueMatchdayLIST
        }
        return ""; // SZUKANY LeagueMatchday NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid LeagueMatchday leagueMatchday, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        leagueMatchdayService.saveLeagueMatchday(leagueMatchday);
        return ""; // LeagueMatchdayLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteLeagueMatchday(@PathVariable Integer id, Model model) {
        model.addAttribute("leagueMatchdayId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteLeagueMatchdayConfirmed(@PathVariable Integer id) {
        Optional<LeagueMatchday> leagueMatchday = leagueMatchdayService.getLeagueMatchdayById(id);

        if (leagueMatchday.isPresent()) {
            leagueMatchdayService.deleteLeagueMatchday(id);
            return ""; // LeagueMatchdayLIST
        } else {
            return ""; // SZUKANY LeagueMatchday NIE ISTNIEJE
        }
    }
}