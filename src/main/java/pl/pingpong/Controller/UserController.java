package pl.pingpong.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.entity.PersonData;
import pl.pingpong.entity.User;
import pl.pingpong.repository.PersonDataRepository;
import pl.pingpong.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PersonDataRepository personDataRepository;

    @GetMapping("/get/list")
    public String getAllUsers(Model model) {
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("usersList", usersList);
        return ""; // WIDOK LISTY
    }

    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user);
            return ""; // WIDOK User
        }
        return ""; // SZUKANY User NIE ISTNIEJE
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/user-form"; // FORM DODAWANIA User
    }

    @PostMapping("/add")
    public String processAddForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/user-form"; // FORM ADD User
        }
        PersonData personData = user.getPersonData();
        personDataRepository.save(personData);

        userService.saveUser(user);
        return "/user/user-form"; //FORM ADD User
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user);
            return ""; //REDIRECT DO UserLIST
        }
        return ""; // SZUKANY User NIE ISTNIEJE
    }

    @PostMapping("/update/{id}")
    public String processUpdateForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return ""; // UPDATE FORM
        }
        userService.saveUser(user);
        return ""; // UserLIST
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model) {
        model.addAttribute("userId", id);
        return ""; //CONFIRMATION DELETE FORM
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteUserConfirmed(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            userService.deleteUser(id);
            return ""; // UserLIST
        } else {
            return ""; // SZUKANY User NIE ISTNIEJE
        }
    }
}