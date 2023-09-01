package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Season;
import pl.pingpong.service.SeasonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/season")
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping("/get/list")
    public String getAllSeasons(Model model) {
        List<Season> seasonsList = seasonService.getAllSeasons();
        model.addAttribute("seasonsList", seasonsList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getSeasonById(@PathVariable Integer id, Model model) {
        Optional<Season> season = seasonService.getSeasonById(id);
        if (season.isPresent()) {
            model.addAttribute("season", season);
            return ""; // WIDOK Season
        }
        return ""; // SZUKANY Season NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("season", new Season());
        return ""; // FORM DODAWANIA Season
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Season season, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD Season
        }
        seasonService.saveSeason(season);
        return ""; //FORM ADD Season
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Season> season = seasonService.getSeasonById(id);
        if (season.isPresent()) {
            model.addAttribute("season", season);
            return ""; //REDIRECT DO SeasonLIST
        }
        return ""; // SZUKANY Season NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Season season, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        seasonService.saveSeason(season);
        return ""; // SeasonLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteSeason(@PathVariable Integer id, Model model) {
        model.addAttribute("seasonId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteSeasonConfirmed(@PathVariable Integer id) {
        Optional<Season> season = seasonService.getSeasonById(id);

        if (season.isPresent()) {
            seasonService.deleteSeason(id);
            return ""; // SeasonLIST
        } else {
            return ""; // SZUKANY Season NIE ISTNIEJE
        }
    }
}