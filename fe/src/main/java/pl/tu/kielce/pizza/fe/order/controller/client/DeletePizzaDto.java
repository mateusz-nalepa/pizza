package pl.tu.kielce.pizza.fe.order.controller.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class DeletePizzaDto {

    private Long pizzaId;
    private Double pizzaPrice;
}
