package pl.tu.kielce.pizza.common.pizza.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto {

    private Long id;
    private String name;
    private String description;
    private Double price;

    private List<IngredientDto> ingredients;

}
