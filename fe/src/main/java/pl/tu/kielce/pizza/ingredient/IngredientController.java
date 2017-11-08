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

@Controller
@RequestMapping("/manager/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    @Autowired
    private final IngredientService ingredientService;

    @GetMapping("/{pantryId}")
    public String ingredientToPantryDefinition(
            @PathVariable("pantryId") Long pantryId,
            Model model) {

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setPantryId(pantryId);
        model.addAttribute("ingredientDto", ingredientDto);
        return "item/item_definition";
    }

    @PostMapping("/")
    public String addNewItem(@Valid IngredientDto ingredientDto, BindingResult bindingResult, Model model) {

        ingredientDto = ingredientService.add(ingredientDto);
        System.out.println("hodoreczek");

        return "redirect:/manager/ingredient/" + ingredientDto.getId();
    }
}
