package pl.tu.kielce.pizza.be.item.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tu.kielce.pizza.ItemServiceTestContextMock;
import pl.tu.kielce.pizza.common.item.service.ItemService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ItemServiceTestContextMock.class)
public class ItemServiceImplTestMock extends BaseItemServiceTest
{
    
    @Autowired
    ItemService itemService;
    
    @Test
    public void shouldReturnFive()
    {
        Long aLong = itemService.returnFive();
    
        Assert.assertEquals(aLong.longValue(), 5L);
    }
    
    @Test
    public void shouldNotReturnFive()
    {
        Assert.assertNotEquals(exampleItem().getPrice(), 5L);
    }
    
}
