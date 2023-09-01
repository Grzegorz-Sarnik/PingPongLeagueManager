package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Club;
import pl.pingpong.service.ClubService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/get/list")
    public String getAllClubs(Model model) {
        List<Club> clubsList = clubService.getAllClubs();
        model.addAttribute("clubsList", clubsList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getClubById(@PathVariable Integer id, Model model) {
        Optional<Club> club = clubService.getClubById(id);
        if (club.isPresent()) {
            model.addAttribute("club", club);
            return ""; // WIDOK CLUB
        }
        return ""; // SZUKANY CLUB NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("club", new Club());
        return ""; // FORM DODAWANIA CLUB
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Club club, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD CLUB
        }
        clubService.saveClub(club);
        return ""; //FORM ADD CLUB
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Club> club = clubService.getClubById(id);
        if (club.isPresent()) {
            model.addAttribute("club", club);
            return ""; //REDIRECT DO CLUBLIST
        }
        return ""; // SZUKANY CLUB NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Club club, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        clubService.saveClub(club);
        return ""; // CLUBLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteClub(@PathVariable Integer id, Model model) {
        model.addAttribute("clubId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteClubConfirmed(@PathVariable Integer id) {
        Optional<Club> club = clubService.getClubById(id);

        if (club.isPresent()) {
            clubService.deleteClub(id);
            return ""; // CLUBLIST
        } else {
            return ""; // SZUKANY CLUB NIE ISTNIEJE
        }
    }
}