package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Trainer;
import pl.pingpong.service.TrainerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping("/get/list")
    public String getAllTrainers(Model model) {
        List<Trainer> trainersList = trainerService.getAllTrainers();
        model.addAttribute("trainersList", trainersList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getTrainerById(@PathVariable Integer id, Model model) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        if (trainer.isPresent()) {
            model.addAttribute("trainer", trainer);
            return ""; // WIDOK Trainer
        }
        return ""; // SZUKANY Trainer NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("trainer", new Trainer());
        return ""; // FORM DODAWANIA Trainer
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Trainer trainer, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD Trainer
        }
        trainerService.saveTrainer(trainer);
        return ""; //FORM ADD Trainer
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        if (trainer.isPresent()) {
            model.addAttribute("trainer", trainer);
            return ""; //REDIRECT DO TrainerLIST
        }
        return ""; // SZUKANY Trainer NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Trainer trainer, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        trainerService.saveTrainer(trainer);
        return ""; // TrainerLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteTrainer(@PathVariable Integer id, Model model) {
        model.addAttribute("trainerId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteTrainerConfirmed(@PathVariable Integer id) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);

        if (trainer.isPresent()) {
            trainerService.deleteTrainer(id);
            return ""; // TrainerLIST
        } else {
            return ""; // SZUKANY Trainer NIE ISTNIEJE
        }
    }
}