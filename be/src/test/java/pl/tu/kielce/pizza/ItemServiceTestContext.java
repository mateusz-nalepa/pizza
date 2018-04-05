package pl.tu.kielce.pizza;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.tu.kielce.pizza.common.item.service.ItemService;

@Configuration
@ComponentScan(basePackages = {
    "pl.tu.kielce.pizza.common.item.service",
    "pl.tu.kielce.pizza.be.item.service",
    "pl.tu.kielce.pizza.be.item.repository",
    "pl.tu.kielce.pizza.be.item.mapper"
})
@EnableJpaRepositories(basePackages = {
    "pl.tu.kielce.pizza.be.item.repository"
})
@EntityScan(basePackages = {
    "pl.tu.kielce.pizza.be.item.model.jpa"
})
public class ItemServiceTestContext
{
}
