package pl.tu.kielce.pizza.fe.ingredient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.ingredient.service.IngredientService;

import javax.validation.Valid;


@Controller
@RequestMapping("/manager/ingredient")
@RequiredArgsConstructor
public class ManagerIngredientController {

    @Autowired
    private final IngredientService ingredientService;

    @GetMapping("/")
    public String ingredientDefinition(Model model) {
        IngredientDto ingredientDto = new IngredientDto();
        model.addAttribute("ingredientDto", ingredientDto);
        return "ingredient/ingredient_definition";
    }

    @PostMapping("/")
    public String addNewIngredient(@Valid IngredientDto ingredientDto, BindingResult bindingResult, Model model) {

        ingredientDto = ingredientService.create(ingredientDto);
        return "redirect:/user/ingredient/" + ingredientDto.getId();
    }
}
