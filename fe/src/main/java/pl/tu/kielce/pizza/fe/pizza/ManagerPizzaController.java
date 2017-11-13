package pl.tu.kielce.pizza.fe.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.ingredient.service.IngredientService;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager/pizza")
@RequiredArgsConstructor
public class ManagerPizzaController {

    @Autowired
    private final PizzaService pizzaService;

    @Autowired
    private final IngredientService ingredientService;

    @GetMapping("/")
    public String pizzaDefinition(Model model) {
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setIngredients(ingredientService.findAll());
        model.addAttribute("pizzaDto", pizzaDto);
        return "pizza/pizza_definition";
    }

    @PostMapping("/")
    public String addNewPizza(
            @Valid PizzaDto pizzaDto,
            BindingResult bindingResult,
            Model model) {

        pizzaDto = pizzaService.create(pizzaDto);
        return "redirect:/user/pizza/" + pizzaDto.getId();
    }

}
