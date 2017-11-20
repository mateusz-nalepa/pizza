package pl.tu.kielce.pizza.fe.pizza.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

import java.util.List;

@Controller
@RequestMapping("/user/pizza")
@RequiredArgsConstructor
public class UserPizzaController {


    @Autowired
    private final PizzaService pizzaService;

    @GetMapping("{pizzaId}")
    public String findOne(
            Model model,
            @PathVariable("pizzaId") Long pizzaId) {

        PizzaDto pizzaDto = pizzaService.findOne(pizzaId);

        model.addAttribute("pizzaDto", pizzaDto);
        return "pizza/pizza_show";
    }

    @GetMapping("/all")
    public String allPizzas(Model model) {
        List<PizzaDto> pizzas = pizzaService.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizza/pizza_search";
    }

}
