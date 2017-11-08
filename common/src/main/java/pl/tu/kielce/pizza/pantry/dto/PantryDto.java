package pl.tu.kielce.pizza.pantry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PantryDto {

    private Long id;
    private List<IngredientDto> ingredients;
}
