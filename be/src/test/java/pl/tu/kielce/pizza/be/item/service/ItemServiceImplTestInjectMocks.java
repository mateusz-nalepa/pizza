package pl.tu.kielce.pizza.be.item.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.tu.kielce.pizza.be.item.repository.ItemExecutor;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTestInjectMocks
{
    
    @Mock
    NewPriceContextUtils newPriceContextUtils;
    
    @Mock
    ItemExecutor itemExecutor;
    
    @InjectMocks
    ItemServiceImpl itemService;
    
    @Test
    public void testSth()
    {
        System.out.println(newPriceContextUtils);
    }
}
