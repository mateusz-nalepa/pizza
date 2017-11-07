package pl.tu.kielce.pizza.ingredient.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.repository.IngredientRepository;
import pl.tu.kielce.pizza.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IngredientExecutor {

    @Autowired
    private final IngredientRepository ingredientRepository;

    @Autowired
    private final IngredientMapper ingredientMapper;

    public IngredientDto getById(Long ingredientId) {
        Ingredient entity = ingredientRepository.findOne(ingredientId);
        return ingredientMapper.entityToDto(entity);
    }

    public IngredientDto add(IngredientDto ingredientDto) {
        Ingredient entity = ingredientMapper.dtoToEntity(ingredientDto);
        entity = ingredientRepository.save(entity);
        return ingredientMapper.entityToDto(entity);
    }

    public List<IngredientDto> findAll() {
        return ingredientRepository
                .findAll()
                .stream()
                .map(ingredientMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
