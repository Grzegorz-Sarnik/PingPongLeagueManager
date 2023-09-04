package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.PersonData;
import pl.pingpong.service.PersonDataService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonDataController {

    private final PersonDataService personDataService;

    @GetMapping("/get/list")
    public String getAllPersonData(Model model) {
        List<PersonData> personDataList = personDataService.getAllPersonData();
        model.addAttribute("personDataList", personDataList);
        return ""; //        return ""; // ---- DODAĆ ------- widok listy
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
            return ""; // ---- DODAĆ ------- UPDATE FORM
        }
        personDataService.savePersonData(personData);
        return ""; // ---- DODAĆ ------- WIDOK LISTY
    }

    @GetMapping("/delete/{id}")
    public String deletePersonData(@PathVariable Integer id, Model model) {
        model.addAttribute("PersonDataId", id);
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
