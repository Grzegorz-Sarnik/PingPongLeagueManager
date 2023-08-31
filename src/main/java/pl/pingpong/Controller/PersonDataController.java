package pl.pingpong.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.pingpong.entity.PersonData;
import pl.pingpong.service.PersonDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonDataController {

    private PersonDataService personDataService;

    public PersonDataController(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/get/list")
    public String getAllPersonData(Model model) {
        List<PersonData> personDataList = personDataService.getAllPersonData();
        model.addAttribute(personDataList);
        return ""; // ---- DODAĆ ------- widok listy
    }

    @GetMapping("/get/{id}")
    public String getPersonDataById(@PathVariable Integer id, Model model) {
        Optional<PersonData> personData = personDataService.getPersonDataById(id);
        if (personData.isPresent()) {
            model.addAttribute("personData", personData);
            return ""; // ---- DODAĆ ------- widok personData
        }
        return ""; // ---- DODAĆ ------- widok - SZUKANA OSOBA NIE ISTNIEJE W BAZIE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("personData", new PersonData());
        return ""; // ---- DODAĆ ------- formularz dodawania użytkownika jsp
    }

    @PostMapping("/add")
    public String processAddForm(@Valid PersonData personData, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // ---- DODAĆ ------- formularz dodawania użytkownika jsp
        }
        personDataService.savePersonData(personData);
        return ""; // ---- DODAĆ ------- formularz dodawania użytkownika jsp
    }

    @GetMapping("update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<PersonData> personData = personDataService.getPersonDataById(id);
        if (personData.isPresent()) {
            model.addAttribute("personData", personData);
            return ""; // ---- DODAĆ ------- formularz edycji użytkownika jsp
        }
        return ""; // ---- DODAĆ ------- widok - SZUKANA OSOBA NIE ISTNIEJE W BAZIE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid PersonData personData, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // ---- DODAĆ ------- redirect do listy
        }
        personDataService.savePersonData(personData);
        return ""; // ---- DODAĆ ------- widok - SZUKANA OSOBA NIE ISTNIEJE W BAZIE
    }

    @GetMapping("/delete/{id}")
    public String deletePersonData(@PathVariable Integer id, Model model) {
        model.addAttribute("PersondataId", id);
        return "";  // ---- DODAĆ ------- formularz potwierdzający usunięcie jsp
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deletePersonDataConfirmed(@PathVariable Integer id) {
        Optional<PersonData> personData = personDataService.getPersonDataById(id);

        if (personData.isPresent()) {
            personDataService.deletePersonData(id);
            return ""; // ---- DODAĆ ------- redirect do listy
        } else {
            return ""; // ---- DODAĆ ------- widok - SZUKANA OSOBA NIE ISTNIEJE W BAZIE
        }
    }
}
