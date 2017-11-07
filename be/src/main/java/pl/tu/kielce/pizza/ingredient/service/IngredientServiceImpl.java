package pl.tu.kielce.pizza.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.IngredientExecutor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{


    @Autowired
    private final IngredientExecutor ingredientExecutor;

    @Override
    public IngredientDto getById(Long ingredientId) {
        return ingredientExecutor.getById(ingredientId);
    }

    @Override
    public IngredientDto add(IngredientDto ingredientDto) {
        return ingredientExecutor.add(ingredientDto);
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientExecutor.findAll();
    }
}
