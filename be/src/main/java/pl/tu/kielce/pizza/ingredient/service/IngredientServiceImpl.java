package pl.tu.kielce.pizza.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.ingredient.dto.FreeItemDto;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.IngredientExecutor;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{


    @Autowired
    private final IngredientExecutor ingredientExecutor;

    @Override
    @Transactional
    public IngredientDto add(IngredientDto ingredientDto) {
        return ingredientExecutor.add(ingredientDto);
    }

    @Override
    public List<FreeItemDto> itemsNotAssignedToDepartment(Long departmentId) {
        return ingredientExecutor.itemsNotAssignedToDepartment(departmentId);
    }

    @Override
    @Transactional
    public void addIngredientToDepartment(IngredientDto ingredientDto, Long departmentId) {
        ingredientExecutor.addIngredientToDepartment(ingredientDto, departmentId);
    }
}
