package pl.tu.kielce.pizza.ingredient.service;

import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;

import java.util.List;

public interface IngredientService {


    IngredientDto getById(Long ingredientId);

    IngredientDto add(IngredientDto ingredientDto);

    List<IngredientDto> findAll();
}
