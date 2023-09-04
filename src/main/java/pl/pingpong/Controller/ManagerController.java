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
import pl.pingpong.entity.PersonData;
import pl.pingpong.service.ManagerService;
import pl.pingpong.service.PersonDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;
    private final PersonDataService personDataService;

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
        return "/manager/add-manager-form"; // FORM DODAWANIA Manager
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) {
            return "/manager/add-manager-form"; // FORM ADD Manager
        }
        PersonData personData = manager.getPersonData();
        personDataService.savePersonData(personData);

        managerService.saveManager(manager);
        return "/manager/add-manager-form"; //FORM ADD Manager
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