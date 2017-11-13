package pl.tu.kielce.pizza.be.ingredient.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Ingredient;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;

@Component
@RequiredArgsConstructor
public class IngredientMapper {

    public IngredientDto entityToDto(Ingredient ingredient) {

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setName(ingredient.getName());
        ingredientDto.setDescription(ingredient.getDescription());

        return ingredientDto;
    }

    public Ingredient dtoToEntity(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        ingredient.setDescription(ingredientDto.getDescription());

        return ingredient;
    }

}
