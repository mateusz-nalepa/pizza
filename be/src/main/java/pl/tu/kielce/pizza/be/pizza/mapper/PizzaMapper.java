package pl.tu.kielce.pizza.be.pizza.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PizzaMapper {


    @Autowired
    private final IngredientMapper ingredientMapper;

    public Pizza dtoToEntity(PizzaDto pizzaDto) {

        Pizza pizza = new Pizza();
        pizza.setName(pizzaDto.getName());
        pizza.setDescription(pizzaDto.getDescription());
        pizza.setPrice(pizzaDto.getPrice());

        return pizza;
    }

    public PizzaDto entityToDto(Pizza pizza) {
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setId(pizza.getId());
        pizzaDto.setName(pizza.getName());
        pizzaDto.setDescription(pizza.getDescription());
        pizzaDto.setPrice(pizza.getPrice());


        List<IngredientDto> ingredientDtos = pizza
                .getIngredients()
                .stream()
                .map(ingredientMapper::entityToDto)
                .collect(Collectors.toList());

        pizzaDto.setIngredients(ingredientDtos);
        return pizzaDto;
    }

}
