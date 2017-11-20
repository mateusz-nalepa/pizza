package pl.tu.kielce.pizza.common.order.dto;

import lombok.*;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoughtItemDto {

    private Long id;

    @Builder.Default
    private Integer quantity = 0;

    private Double purchasePrice;

    private ItemDto item;

    public Integer increaseQuantity() {
        return ++this.quantity;
    }
}
