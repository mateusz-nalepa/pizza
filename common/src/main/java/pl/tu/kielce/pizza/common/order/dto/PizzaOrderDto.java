package pl.tu.kielce.pizza.common.order.dto;

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
public class PizzaOrderDto {

        private Long id;
        private String name;
        private String description;
        private Double price;

        private List<IngredientDto> ingredients;

}
