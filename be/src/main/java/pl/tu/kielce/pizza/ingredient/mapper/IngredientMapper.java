package pl.tu.kielce.pizza.ingredient.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.repository.ItemRepository;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

@Component
@RequiredArgsConstructor
public class IngredientMapper {

    @Autowired
    private final ItemMapper itemMapper;

    @Autowired
    private final ItemRepository itemRepository;

    public IngredientDto entityToDto(Ingredient entity) {

        return IngredientDto
                .builder()
                .id(entity.getId())
                .itemDto(itemMapper.entityToDto(entity.getItem()))
                .quantity(entity.getQuantity())
                .pantryId(entity.getPantryId())
                .build();
    }

    public Ingredient dtoToEntity(IngredientDto dto) {

        return Ingredient
                .builder()
                .pantryId(dto.getPantryId())
                .item(itemRepository.findOne(dto.getItemDto().getId()))
                .quantity(dto.getQuantity())
                .build();
    }
}
