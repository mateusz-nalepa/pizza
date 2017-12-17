package pl.tu.kielce.pizza.common.order.dto;

import lombok.*;
import pl.tu.kielce.pizza.common.common.enums.PizzaType;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoughtPizzaDto {

    private Long id;

//    private Order order;
    private Double purchasePrice;

    @Builder.Default
    private Integer quantity = 0;

    private PizzaType pizzaType;

//    private DepartmentDto department;

//    @OneToOne
    private PizzaDto pizza;

    public Integer increaseQuantity() {
        return ++this.quantity;
    }

    public Integer decreaseQuantity() {
        return --this.quantity;
    }
}
