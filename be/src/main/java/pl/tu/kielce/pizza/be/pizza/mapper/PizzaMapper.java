package pl.tu.kielce.pizza.be.pizza.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.ingredient.mapper.IngredientMapper;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Ingredient;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PizzaMapper {


    @Autowired
    private final IngredientMapper ingredientMapper;

    public Pizza dtoToEntity(PizzaDto pizzaDto) {

        Pizza pizza = new Pizza();
        pizza.setName(pizzaDto.getName());
//        pizza.setDescription(pizzaDto.getDescription());
        pizza.setPrice(pizzaDto.getPrice());

        return pizza;
    }

    public PizzaDto entityToDto(Pizza pizza) {
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setId(pizza.getId());
        pizzaDto.setName(pizza.getName());
        String desc = fetchPizzaDesc(pizza);
        pizzaDto.setDescription(desc);



        pizzaDto.setPrice(pizza.getPrice());


        List<IngredientDto> ingredientDtos = pizza
                .getIngredients()
                .stream()
                .map(ingredientMapper::entityToDto)
                .collect(Collectors.toList());

        pizzaDto.setIngredients(ingredientDtos);
        return pizzaDto;
    }

    private String fetchPizzaDesc(Pizza pizza) {
        Set<Ingredient> ingredients = pizza.getIngredients();

        StringBuilder desc = new StringBuilder();

        for (Ingredient ingredient : ingredients) {
            desc.append(ingredient.getName()).append(", ");
        }

        String s = desc.toString();
        return s.substring(0, s.length()-2);
    }

}
