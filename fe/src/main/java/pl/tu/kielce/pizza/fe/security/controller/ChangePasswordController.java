package pl.tu.kielce.pizza.fe.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.security.dto.ChangePasswordDto;
import pl.tu.kielce.pizza.common.security.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/changePassword")
public class ChangePasswordController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public String changePasswordGet(Model model) {

        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "user/change_password";
    }

    @PostMapping
    public String changePasswordPost(
            @Valid ChangePasswordDto changePasswordDto,
            BindingResult bindingResult,
            Model model) {

        userService.changePassword(changePasswordDto);
        return "redirect:/logout";
    }

}
