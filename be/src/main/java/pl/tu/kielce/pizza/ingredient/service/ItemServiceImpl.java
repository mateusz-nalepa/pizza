package pl.tu.kielce.pizza.ingredient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.ingredient.dto.ItemDto;
import pl.tu.kielce.pizza.ingredient.executor.ItemExecutor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {


    @Autowired
    private final ItemExecutor itemExecutor;

    @Override
    public ItemDto getById(Long itemId) {
        return itemExecutor.getById(itemId);
    }

    @Override
    public ItemDto add(ItemDto itemDto) {
        return itemExecutor.add(itemDto);
    }

    @Override
    public List<ItemDto> findAll() {
        return itemExecutor.findAll();
    }
}
