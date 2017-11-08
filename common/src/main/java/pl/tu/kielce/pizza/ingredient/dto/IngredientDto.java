package pl.tu.kielce.pizza.ingredient.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {


    private Long pantryId;

    private Long id;
    private ItemDto itemDto;
    private Double quantity;
}
