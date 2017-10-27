package pl.tu.kielce.pizza.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tu.kielce.pizza.messages.MessageSourceAccessor;

@RestController
@RequestMapping("language")
public class LanguageController {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @GetMapping("/text")
    @ResponseBody
    public String en(@RequestParam(value = "messageCode", required = false) String messageCode) {

        System.out.println(messageSourceAccessor.getMessage("dupa", 1, 2));
        System.out.println(messageSourceAccessor.getMessage("napis"));
        return "HODOR";
    }
}
