package pl.tu.kielce.pizza;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.tu.kielce.pizza.common.item.service.ItemService;

@Configuration
@ComponentScan(basePackages = {
    "pl.tu.kielce.pizza.common.item.service"
})
public class ItemServiceTestContext
{
    @Bean
    public ItemService itemService()
    {
        ItemService mock = Mockito.mock(ItemService.class);
        Mockito
            .when(mock.returnFive())
            .thenReturn(5L);
        return mock;
    }
}
