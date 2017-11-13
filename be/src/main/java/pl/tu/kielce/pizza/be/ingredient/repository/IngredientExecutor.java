package pl.tu.kielce.pizza.be.ingredient.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Ingredient;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IngredientExecutor {


    @Autowired
    private final IngredientRepository ingredientRepository;

    @Autowired
    private final IngredientMapper ingredientMapper;

    public List<IngredientDto> findAll() {

        return ingredientRepository
                .findAll()
                .stream()
                .map(ingredientMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public IngredientDto create(IngredientDto ingredientDto) {

        Ingredient ingredient = ingredientMapper.dtoToEntity(ingredientDto);
        ingredient = ingredientRepository.save(ingredient);
        return ingredientMapper.entityToDto(ingredient);
    }


    public IngredientDto findOne(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findOne(ingredientId);
        return ingredientMapper.entityToDto(ingredient);
    }
}
