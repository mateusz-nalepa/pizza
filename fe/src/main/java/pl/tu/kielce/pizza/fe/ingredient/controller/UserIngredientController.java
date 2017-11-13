package pl.tu.kielce.pizza.fe.ingredient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.ingredient.service.IngredientService;

import java.util.List;


@Controller
@RequestMapping("/user/ingredient")
@RequiredArgsConstructor
public class UserIngredientController {

    @Autowired
    private final IngredientService ingredientService;

    @GetMapping("{ingredientId}")
    public String findOne(
            Model model,
            @PathVariable("ingredientId") Long ingredientId) {

        IngredientDto ingredientDto = ingredientService.findOne(ingredientId);

        model.addAttribute("ingredientDto", ingredientDto);
        return "ingredient/ingredient_show";
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public String allIngredients(Model model) {
        List<IngredientDto> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredient/ingredient_search";
    }

}
