package pl.tu.kielce.pizza.ingredient.service;

import pl.tu.kielce.pizza.ingredient.dto.FreeItemDto;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;

import java.util.List;

public interface IngredientService {

    IngredientDto add(IngredientDto ingredientDto);

    List<FreeItemDto> itemsNotAssignedToDepartment(Long departmentId);

    void addIngredientToDepartment(IngredientDto itemDto, Long departmentId);
}
