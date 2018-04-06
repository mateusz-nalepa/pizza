package pl.tu.kielce.pizza.be.item.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tu.kielce.pizza.be.item.repository.ItemExecutor;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTestSpy extends BaseItemServiceTest
{
    
    @Mock
    private NewPriceContextUtils newPriceContextUtils;
    
    @Mock
    private ItemExecutor itemExecutor;
    
    @Spy
    @InjectMocks
    private ItemServiceImpl itemService;
    
    @Test
    public void testSth()
    {
        itemService.create(exampleItem());
        Mockito.verify(itemService, times(1)).create(exampleItem());
    }
    
}
