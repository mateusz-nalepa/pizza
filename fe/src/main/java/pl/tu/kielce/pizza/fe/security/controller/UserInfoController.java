package pl.tu.kielce.pizza.fe.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class UserInfoController {

    @Autowired
    private final UserService userService;

    @GetMapping(value = "/showProfile")
    @PreAuthorize("hasAuthority('CLIENT')")
    public String showProfile(Model model) {
        UserDto userDto = UserUtils
                            .getUserDto()
                            .orElse(null);

        model.addAttribute("userDto", userDto);
        return "client/show_profile";
    }

    @PostMapping("/storeAvatar")
    public String storeAvatar(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        userService.storeAvatar(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/client/showProfile";
    }

}
