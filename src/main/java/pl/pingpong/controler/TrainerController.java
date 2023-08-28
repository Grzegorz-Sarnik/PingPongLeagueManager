package pl.pingpong.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.dao.TrainerDao;
import pl.pingpong.entity.Trainer;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    private TrainerDao trainerDao;

    public TrainerController(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<Trainer> trainer = trainerDao.findAll();
        model.addAttribute("trainer", trainer);
        return "/trainer/list-trainer";

    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("trainer", new Trainer());
        return "/trainer/add-trainer-form";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Trainer trainer, BindingResult result) {
        if (result.hasErrors()) return "/trainer/add-trainer-form";
        trainerDao.addTrainer(trainer);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdatetrainer(@PathVariable int id, Model model) {
        Trainer trainer = trainerDao.findById(id);
        model.addAttribute("trainer", trainer);
        return "/trainer/update-trainer-form";
    }

    @PostMapping("/update/{id}")
    public String processUpdatetrainer(@Valid Trainer trainer, BindingResult result) {
        if (result.hasErrors())
            return "/trainer/update-trainer-form";
        trainerDao.updateTrainer(trainer);
        return "redirect:/list";
    }

    @GetMapping("/remove/{id}")
    public String removetrainer(@PathVariable int id, Model model) {
        model.addAttribute("trainerId", id);
        return "/trainer/remove-trainer-form";
    }

    @PostMapping("remove/{id}/confirmed")
    public String removetrainerConfirm(@PathVariable int id) {
        Trainer trainer = trainerDao.findById(id);
        trainerDao.removeTrainer(trainer);
        return "redirect:/list";
    }
}