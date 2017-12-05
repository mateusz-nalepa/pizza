package pl.tu.kielce.pizza.fe.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.RoleService;
import pl.tu.kielce.pizza.common.security.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserController {

    private static final String USER_REGISTRATION_TEMPLATE_PATH = "user/registration";
    private static final String USER_LOGIN_TEMPLATE_PATH = "user/login";
    private static final String REGISTRATION_URL = "/registration";
    private static final String LOGIN_URL = "login";

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoleService roleService;

    @GetMapping(LOGIN_URL)
    public String login() {
        return USER_LOGIN_TEMPLATE_PATH;
    }

    @RequestMapping(value = REGISTRATION_URL, method = RequestMethod.GET)
    public String registration(Model model) {
        UserDto userDto = new UserDto();
        Set<RoleDto> all = roleService.findAll();
        ArrayList<RoleDto> roleDtos = new ArrayList<>(all);
        userDto.setRoles(roleDtos);
        model.addAttribute("userDto", userDto);
        return USER_REGISTRATION_TEMPLATE_PATH;
    }

    @RequestMapping(value = REGISTRATION_URL, method = RequestMethod.POST)
    public String createNewUser(@Valid UserDto userDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Tu sa bledy walidacji!");
            return USER_REGISTRATION_TEMPLATE_PATH;
        }

        UserDto userByEmail = userService.findByEmail(userDto.getEmail());

        if (Objects.nonNull(userByEmail)) {
            model.addAttribute("errorMessage", "Uzytkownik istnieje");
            return USER_REGISTRATION_TEMPLATE_PATH;
        }

        userService.saveUser(userDto);
        model.addAttribute("successMessage", "User has been registered successfully");
        model.addAttribute("user", new UserDto());
        return USER_REGISTRATION_TEMPLATE_PATH;
    }

}
