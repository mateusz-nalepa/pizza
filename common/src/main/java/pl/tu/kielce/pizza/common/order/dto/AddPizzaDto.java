package pl.tu.kielce.pizza.common.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPizzaDto {

    private Long pizzaId;
    private int pizzaType;
}
