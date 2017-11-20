package pl.tu.kielce.pizza.be.order.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.tu.kielce.pizza.common.order.dto.BoughtItemDto;
import pl.tu.kielce.pizza.common.order.dto.BoughtPizzaDto;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;

@Component
@SessionScope
@Getter
@Setter
public class UserOrderInfo {

    private UserOrderDto userOrderDto = new UserOrderDto();

    public void addPizzaToOrder(BoughtPizzaDto boughtPizzaDto) {
        boughtPizzaDto.increaseQuantity();
        userOrderDto.getBoughtPizzas().add(boughtPizzaDto);
        Double newTotalPrice = userOrderDto.getTotalPrice() + boughtPizzaDto.getPurchasePrice();
        userOrderDto.setTotalPrice(newTotalPrice);
    }


    public void addItemToOrder(BoughtItemDto boughtItemDto) {
        boughtItemDto.increaseQuantity();
        userOrderDto.getBoughtItems().add(boughtItemDto);
        Double newTotalPrice = userOrderDto.getTotalPrice() + boughtItemDto.getPurchasePrice();
        userOrderDto.setTotalPrice(newTotalPrice);
    }
}
