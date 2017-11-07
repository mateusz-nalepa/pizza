package pl.tu.kielce.pizza.ingredient;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.service.IngredientService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager/ingredient")
@RequiredArgsConstructor
public class IngredientController {


    @Autowired
    private final IngredientService ingredientService;

    @GetMapping("{ingredientId}")
    public String getIngredientId(
            Model model,
            @PathVariable("ingredientId") Long ingredientId) {

        IngredientDto ingredientDto = ingredientService.getById(ingredientId);

        model.addAttribute("ingredientDto", ingredientDto);
        return "ingredient/ingredient_show";
    }

    @GetMapping("/")
    public String ingredientDefinition(Model model) {
        IngredientDto ingredientDto = new IngredientDto();
        model.addAttribute("ingredientDto", ingredientDto);
        return "ingredient/ingredient_definition";
    }

    @PostMapping("/")
    public String addNewIngredient(@Valid IngredientDto ingredientDto, BindingResult bindingResult, Model model) {

        ingredientDto = ingredientService.add(ingredientDto);
        System.out.println("hodoreczek");

        return "redirect:/manager/ingredient/" + ingredientDto.getId();
    }

    @GetMapping("/all")
    public String allIngredient(Model model) {
        List<IngredientDto> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredient/ingredient_search";
    }

}
