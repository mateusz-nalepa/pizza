package pl.tu.kielce.pizza.ingredient.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.ingredient.dto.ItemDto;
import pl.tu.kielce.pizza.ingredient.executor.repository.ItemRepository;
import pl.tu.kielce.pizza.ingredient.mapper.ItemMapper;
import pl.tu.kielce.pizza.ingredient.model.jpa.Item;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemExecutor {

    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    private final ItemMapper itemMapper;

    public ItemDto getById(Long itemId) {
        Item entity = itemRepository.findOne(itemId);
        return itemMapper.entityToDto(entity);
    }

    public ItemDto add(ItemDto itemDto) {
        Item entity = itemMapper.dtoToEntity(itemDto);
        entity = itemRepository.save(entity);
        return itemMapper.entityToDto(entity);
    }

    public List<ItemDto> findAll() {
        return itemRepository
                .findAll()
                .stream()
                .map(itemMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
