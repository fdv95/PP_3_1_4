package ru.fadeev.pp_3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fadeev.pp_3_1_2.dao.UserRepository;
import ru.fadeev.pp_3_1_2.model.User;
import ru.fadeev.pp_3_1_2.service.RoleService;
import ru.fadeev.pp_3_1_2.service.UserService;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, RoleService roleService,
                           UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

    @GetMapping("/new")
    public String getFormToAddNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "new";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getFormToUpdateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") Long id, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        User userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb != null && !userFromDb.getId().equals(user.getId())) {
            model.put("message", "User exists!");
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/edit/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
