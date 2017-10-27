package pl.tu.kielce.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tu.kielce.pizza.security.mapper.UserMapper;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.model.jpa.User;
import pl.tu.kielce.pizza.security.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class DefaultController {

//    @GetMapping("/")
//    public String home1() {
//        return "/home";
//    }
//
//    @GetMapping("/home")
//    public String home() {
//        return "/home";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "/admin";
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return "/user";
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "/about";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "/login";
//    }
//
//    @GetMapping("/403")
//    public String error403() {
//        return "/error/403";
//    }

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value={"/", "/login"})
    public String login(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
        return "login";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {

            Role role = new Role();
            role.setRole("ADMIN");
            Set<Role> asd = new HashSet<>();
            user.setRoles(asd);
            user.setEmail(user.getEmail());
            userService.saveUser(userMapper.entityToDto(user));
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
        }
            return "registration";
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
        return "admin/home";
    }

    @GetMapping("access-denied")
    public String asd() {
        return "access-denied";
    }

}
