package pl.tu.kielce.pizza;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "pl.tu.kielce.pizza.empty"
})
public class PizzaApplicationTestContext
{
}
