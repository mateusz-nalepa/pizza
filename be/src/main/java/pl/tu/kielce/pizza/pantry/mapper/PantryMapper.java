package pl.tu.kielce.pizza.pantry.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.pantry.dto.PantryDto;
import pl.tu.kielce.pizza.pantry.model.jpa.Pantry;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PantryMapper {

    private final IngredientMapper ingredientMapper;

    public PantryDto entityToDto(Pantry pantryEntity) {
        List<IngredientDto> ingredients = pantryEntity
                .getIngredients()
                .stream()
                .map(ingredientMapper::entityToDto)
                .collect(Collectors.toList());

        return PantryDto
                .builder()
                .id(pantryEntity.getId())
                .ingredients(ingredients)
                .build();
    }
}
