package pl.tu.kielce.pizza.fe.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tu.kielce.pizza.common.messages.MessageSourceAccessor;

@RestController
@RequestMapping("language")
public class LanguageController {

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    @GetMapping("/text")
    @ResponseBody
    public String en(@RequestParam(value = "messageCode", required = false) String messageCode) {

        System.out.println(messageSourceAccessor.getMessage("napis"));
        return "HODOR";
    }
}
