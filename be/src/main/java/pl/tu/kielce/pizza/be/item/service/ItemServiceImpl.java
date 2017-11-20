package pl.tu.kielce.pizza.be.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.be.item.repository.ItemExecutor;
import pl.tu.kielce.pizza.common.common.util.PriceUtils;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemExecutor itemExecutor;

    @Override
    public ItemDto findOne(Long itemId) {
        ItemDto itemDto = itemExecutor.findOne(itemId);
        return setPrice(itemDto);
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
        return itemExecutor.add(itemDto);
    }

    @Override
    public List<ItemDto> findAll() {
        return itemExecutor.findAll();
    }

    @Override
    public List<ItemDto> findAllWithMultiplier() {

        return findAll()
                .stream()
                .map(this::setPrice)
                .collect(Collectors.toList());
    }

    private ItemDto setPrice(ItemDto itemDto) {
        Double newPrice = PriceUtils.priceWithMultiplier(itemDto.getPrice());
        itemDto.setPrice(newPrice);
        return itemDto;
    }
}
