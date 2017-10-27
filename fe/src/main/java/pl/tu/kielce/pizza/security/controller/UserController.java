package pl.tu.kielce.pizza.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.tu.kielce.pizza.dto.user.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

//@Controller
//@RequiredArgsConstructor
public class UserController {
//
//    @GetMapping("registration")
//    public String registration(HttpServletRequest request, Model model) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
////        UserProfile userProfile = (UserProfile) authentication;
//
//        System.out.println(request.getRequestURI());
//
//
//        model.addAttribute("registrationForm", new RegistrationForm());
////        model.addAttribute("submitMapping", "/registration/create");
//        return "/user/user_definition";
//    }
//
////    @PostMapping("registration/create")
////    public String registrationDupa(
////            @Valid RegistrationForm registrationForm,  BindingResult bindingResult, HttpServletRequest request, Model model) {
////        System.out.println(request.getRequestURI());
////
////
////        if (bindingResult.hasErrors()) {
////            return "/user/user_definition";
////
////        }
////
////        model.addAttribute("registrationForm", new RegistrationForm());
//////        model.addAttribute("submitMapping", "/registration/create");
////        return "/user/user_definition";
////    }
//
//    @PostMapping("registration")
//    public String register(Model model, @Valid RegistrationForm registrationForm, BindingResult bindingResult, RedirectAttributes attr) {
//
//        if (bindingResult.hasErrors()) {
//            return "user/user_definition";
//        }
//
//
//        model.addAttribute("napis",
//                "BEGIN<br />" +
//                        registrationForm.getUserName() +
//                        "<br />END");
//        return "user/user_definition";
//    }



}


//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        Locale en = new Locale("pl");
//        Integer[] objects = new Integer[]{1,5};
//        System.out.println(messageSource.getMessage("dupa", objects, en));
//        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");