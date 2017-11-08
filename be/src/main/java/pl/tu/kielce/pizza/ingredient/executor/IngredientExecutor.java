package pl.tu.kielce.pizza.ingredient.executor;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.repository.IngredientRepository;
import pl.tu.kielce.pizza.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;
import pl.tu.kielce.pizza.pantry.model.jpa.Pantry;
import pl.tu.kielce.pizza.pantry.repository.PantryRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IngredientExecutor {


    @Autowired
    private final IngredientMapper ingredientMapper;

    @Autowired
    private final PantryRepository pantryRepository;

    @Autowired
    private final IngredientRepository ingredientRepository;

    @Transactional
    public IngredientDto add(IngredientDto ingredientDto) {
        Pantry pantryEntity = pantryRepository.findOne(ingredientDto.getPantryId());
        List<Ingredient> ingredients = pantryEntity.getIngredients();
        List<Ingredient> updatedIngredients = new ArrayList<>(ingredients);
        Ingredient entity = ingredientMapper.dtoToEntity(ingredientDto);
        entity = ingredientRepository.save(entity);
        Long id = entity.getId();
        updatedIngredients.add(entity);
        pantryEntity.setIngredients(updatedIngredients);
        pantryRepository.save(pantryEntity);
        ingredientDto.setId(id);
        return ingredientDto;

    }
}
