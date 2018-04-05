package pl.tu.kielce.pizza.be.item.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tu.kielce.pizza.ItemServiceTestContext;
import pl.tu.kielce.pizza.ItemServiceTestContextMock;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ItemServiceTestContext.class)
public class ItemServiceImplTest
{
    
    @Autowired
    ItemService itemService;
    
    @Test
    public void shouldAddItemToDatabase()
    {
        
        //TODO add save on repo!
        ItemDto itemDto = itemService.create(exampleItem());
    
        Assert.assertNotNull(itemDto.getId());
    }
    
    @Test
    public void shouldNotReturnFive()
    {
        Assert.assertNotEquals(exampleItem().getPrice(), 5L);
    }
    
    private ItemDto exampleItem()
    {
        return ItemDto
            .builder()
            .name("Example Item")
            .price(12D)
            .build();
    }
}
