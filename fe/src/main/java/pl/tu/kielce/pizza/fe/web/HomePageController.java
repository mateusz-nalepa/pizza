package pl.tu.kielce.pizza.fe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private static final String HOME_PAGE_TEMPLATE_PATH = "home-page";
    private static final String HOME_PAGE_URL = "/";

    @GetMapping(value = {"", "/"})
    public String homePage() {
        return HOME_PAGE_TEMPLATE_PATH;
    }
}
