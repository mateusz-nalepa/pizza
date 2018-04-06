package pl.tu.kielce.pizza.be.item.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.item.mapper.ItemMapper;
import pl.tu.kielce.pizza.be.item.model.jpa.Item;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemExecutor {


    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    private final ItemMapper itemMapper;

    public List<ItemDto> findAll() {

        return itemRepository
                .findAll()
                .stream()
                .map(itemMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ItemDto add(ItemDto itemDto) {

        Item item = itemMapper.dtoToEntity(itemDto);
        item = itemRepository.save(item);
        return itemMapper.entityToDto(item);
    }


    public ItemDto findOne(Long itemId) {
        Item item = itemRepository.findOne(itemId);
        return itemMapper.entityToDto(item);
    }
    
    public String returnString(String str) {
        return str;
    }
    
    public void voidMethod() {}
}
