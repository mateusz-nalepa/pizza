package pl.tu.kielce.pizza.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    private static final String ACCESS_DENIED_URL = "access-denied";
    private static final String ERROR_ACCESS_DENIED_TEMPLATE_PATH = "error/access-denied";

    @GetMapping(ACCESS_DENIED_URL)
    public String accessDenied() {
        return ERROR_ACCESS_DENIED_TEMPLATE_PATH;
    }

}
