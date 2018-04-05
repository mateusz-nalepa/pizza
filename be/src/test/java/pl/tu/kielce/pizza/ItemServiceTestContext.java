package pl.tu.kielce.pizza;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;
import pl.tu.kielce.pizza.common.order.session.UserContext;

@Configuration
@EnableAutoConfiguration(exclude = {
    JpaRepositoriesAutoConfiguration.class
})
@ComponentScan(basePackages = {
    "pl.tu.kielce.pizza.common.item.service",
    "pl.tu.kielce.pizza.be.item.service",
    "pl.tu.kielce.pizza.be.item.repository",
    "pl.tu.kielce.pizza.be.item.mapper"
})

@EntityScan(basePackages = {
    "pl.tu.kielce.pizza.be.item.model.jpa"
})
@EnableJpaRepositories(basePackages = {
    "pl.tu.kielce.pizza.be.item.repository"
})
public class ItemServiceTestContext
{
    
    @Bean
    public NewPriceContextUtils newPriceContextUtils()
    {
        return Mockito.mock(NewPriceContextUtils.class);
    }
    
    @Bean
    public UserContext userContext()
    {
        return Mockito.mock(UserContext.class);
    }
}
