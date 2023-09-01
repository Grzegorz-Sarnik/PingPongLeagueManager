package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.Manager;
import pl.pingpong.service.ManagerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/get/list")
    public String getAllManagers(Model model) {
        List<Manager> managersList = managerService.getAllManagers();
        model.addAttribute("managersList", managersList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getManagerById(@PathVariable Integer id, Model model) {
        Optional<Manager> manager = managerService.getManagerById(id);
        if (manager.isPresent()) {
            model.addAttribute("Manager", manager);
            return ""; // WIDOK Manager
        }
        return ""; // SZUKANY Manager NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("manager", new Manager());
        return ""; // FORM DODAWANIA Manager
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // FORM ADD Manager
        }
        managerService.saveManager(manager);
        return ""; //FORM ADD Manager
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Manager> manager = managerService.getManagerById(id);
        if (manager.isPresent()) {
            model.addAttribute("manager", manager);
            return ""; //REDIRECT DO ManagerLIST
        }
        return ""; // SZUKANY Manager NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        managerService.saveManager(manager);
        return ""; // ManagerLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteManager(@PathVariable Integer id, Model model) {
        model.addAttribute("managerId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteManagerConfirmed(@PathVariable Integer id) {
        Optional<Manager> manager = managerService.getManagerById(id);

        if (manager.isPresent()) {
            managerService.deleteManager(id);
            return ""; // ManagerLIST
        } else {
            return ""; // SZUKANY Manager NIE ISTNIEJE
        }
    }
}