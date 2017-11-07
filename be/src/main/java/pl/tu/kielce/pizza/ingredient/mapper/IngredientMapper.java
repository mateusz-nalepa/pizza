package pl.tu.kielce.pizza.ingredient.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

@Component
@RequiredArgsConstructor
public class IngredientMapper {

    public IngredientDto entityToDto(Ingredient entity) {
        return IngredientDto
                .builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .name(entity.getName())
                .build();
    }

    public Ingredient dtoToEntity(IngredientDto dto) {
        return Ingredient
                .builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .name(dto.getName())
                .build();
    }
}
