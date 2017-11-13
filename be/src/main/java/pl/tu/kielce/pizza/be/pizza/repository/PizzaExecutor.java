package pl.tu.kielce.pizza.be.pizza.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.ingredient.repository.IngredientRepository;
import pl.tu.kielce.pizza.be.pizza.mapper.PizzaMapper;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Ingredient;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PizzaExecutor {

    @Autowired
    private final PizzaRepository pizzaRepository;

    @Autowired
    private final IngredientRepository ingredientRepository;

    @Autowired
    private final PizzaMapper pizzaMapper;

    public PizzaDto create(PizzaDto pizzaDto) {
        Pizza pizza = pizzaMapper.dtoToEntity(pizzaDto);

        Set<Ingredient> ingredients = fetchIngredients(pizzaDto);
        pizza.setIngredients(ingredients);
        pizza.activate();
        pizza = pizzaRepository.save(pizza);

        return pizzaMapper.entityToDto(pizza);
    }

    private Set<Ingredient> fetchIngredients(PizzaDto pizzaDto) {

        List<Long> ingredientsIds = pizzaDto
                .getIngredients()
                .stream()
                .filter(IngredientDto::isSelected)
                .map(IngredientDto::getId)
                .collect(Collectors.toList());

        return ingredientRepository.findByIngredientsIds(ingredientsIds);
    }

    public List<PizzaDto> findAll()
    {
        return pizzaRepository
                .findAllActive()
                .stream()
                .map(pizzaMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public PizzaDto findOne(Long pizzaId) {
        Pizza pizza = pizzaRepository.findOne(pizzaId);

        return pizzaMapper.entityToDto(pizza);
    }
}
