package pl.tu.kielce.pizza.be.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.ingredient.repository.IngredientExecutor;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.ingredient.service.IngredientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private final IngredientExecutor ingredientExecutor;

    @Override
    public IngredientDto findOne(Long ingredientId) {
        return ingredientExecutor.findOne(ingredientId);
    }

    @Override
    @Transactional
    public IngredientDto create(IngredientDto ingredientDto) {
        return ingredientExecutor.create(ingredientDto);
    }

    @Override
    public List<IngredientDto> findAll() {
        return ingredientExecutor.findAll();
    }
}
