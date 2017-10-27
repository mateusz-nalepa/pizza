package pl.tu.kielce.pizza.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tu.kielce.pizza.security.dto.UserDto;
import pl.tu.kielce.pizza.security.service.UserService;

@Controller
@RequestMapping(AdminController.ADMIN)
@RequiredArgsConstructor
public class AdminController {

    static final String ADMIN = "/admin";
    private static final String HOME_URL = "/home";
    private static final String ADMIN_HOME_PAGE_TEMPLATE_PATH = "admin/home";

    @Autowired
    private final UserService userService;

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = HOME_URL, method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.findByEmail(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        model.addAttribute("adminMessage", "Content Available Only for Users with Admin Role");
        return ADMIN_HOME_PAGE_TEMPLATE_PATH;
    }
}
