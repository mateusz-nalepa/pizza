package pl.tu.kielce.pizza.ingredient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.tu.kielce.pizza.ingredient.dto.FreeItemDto;
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

    @GetMapping("/department/add/{departmentId}")
    public String addIngredientToDepartment(
            @PathVariable("departmentId") Long departmentId,
            Model model) {

        model.addAttribute("itemsNotAssignedToDepartment", ingredientService.itemsNotAssignedToDepartment(departmentId));
        model.addAttribute("ingredientDto", new IngredientDto());
        return "item/add_ingredient_to_department";
    }

    @PostMapping("/department/add/{departmentId}")
    public String addIngredientToDepartmentPost(
            @Valid IngredientDto ingredientDto,
            BindingResult bindingResult,
            @PathVariable("departmentId") Long departmentId
    ) {

        ingredientService.addIngredientToDepartment(ingredientDto, departmentId);

        return "redirect:/manager/department/" + departmentId;
    }




    @ModelAttribute("asd")
    public List<FreeItemDto> freeManagers() {
        return null;
    }

}
