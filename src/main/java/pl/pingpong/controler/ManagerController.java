package pl.pingpong.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pingpong.dao.ManagerDao;
import pl.pingpong.entity.Manager;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private ManagerDao managerDao;

    public ManagerController(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<Manager> managers = managerDao.findAll();
        model.addAttribute("managers", managers);
        return "/manager/list-manager";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("manager", new Manager());
        return "/manager/add-manager-form";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) return "/manager/add-manager-form";
        managerDao.addManager(manager);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateManager(@PathVariable int id, Model model) {
        Manager manager = managerDao.findById(id);
        model.addAttribute("manager", manager);
        return "/manager/update-manager-form";
    }

    @PostMapping("/update/{id}")
    public String processUpdateManager(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors())
            return "/manager/update-manager-form";
        managerDao.updateManager(manager);
        return "redirect:/list";
    }

    @GetMapping("/remove/{id}")
    public String removeManager(@PathVariable int id, Model model) {
        model.addAttribute("managerId", id);
        return "/manager/remove-manager-form";
    }

    @PostMapping("remove/{id}/confirmed")
    public String removeManagerConfirm(@PathVariable int id) {
        Manager manager = managerDao.findById(id);
        managerDao.removeManager(manager);
        return "redirect:/list";
    }
}