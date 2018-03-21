package pl.tu.kielce.pizza.be.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.be.item.repository.ItemExecutor;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemExecutor itemExecutor;

    @Autowired
    private final NewPriceContextUtils newPriceContextUtils;

    @Override
    public ItemDto findOne(Long itemId) {
        ItemDto itemDto = itemExecutor.findOne(itemId);
        itemDto.setPrice(newPriceContextUtils.priceWithMultiplier(itemDto.getPrice()));
        return itemDto;
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
                .peek(itemDto -> itemDto.setPrice(newPriceContextUtils.priceWithMultiplier(itemDto.getPrice())))
                .collect(Collectors.toList());
    }
    
    @Override
    public Long returnFive()
    {
        return 5L;
    }
    
}
