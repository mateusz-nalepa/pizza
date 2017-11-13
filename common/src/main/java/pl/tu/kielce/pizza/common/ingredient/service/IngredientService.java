package pl.tu.kielce.pizza.common.ingredient.service;


import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;

import java.util.List;

public interface IngredientService {

    IngredientDto findOne(Long ingredientId);

    IngredientDto create(IngredientDto ingredientDto);

    List<IngredientDto> findAll();
}
