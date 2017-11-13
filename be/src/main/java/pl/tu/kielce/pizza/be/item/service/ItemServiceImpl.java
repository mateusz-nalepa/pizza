package pl.tu.kielce.pizza.be.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.be.item.repository.ItemExecutor;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemExecutor itemExecutor;

    @Override
    public ItemDto findOne(Long itemId) {
        return itemExecutor.findOne(itemId);
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        return itemExecutor.add(itemDto);
    }

    @Override
    public List<ItemDto> findAll() {
        return itemExecutor.findAll();
    }
}
