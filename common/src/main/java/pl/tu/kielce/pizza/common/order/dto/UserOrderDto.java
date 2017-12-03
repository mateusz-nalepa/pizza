package pl.tu.kielce.pizza.common.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tu.kielce.pizza.common.common.enums.OrderStatus;
import pl.tu.kielce.pizza.common.common.enums.OrderType;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserOrderDto {

    private Long id;
//    private User buyer;
//
    private List<BoughtItemDto> boughtItems = new ArrayList<>();
//
    private List<BoughtPizzaDto> boughtPizzas = new ArrayList<>();
//
//    private AddressDto addressDto;

    private DeliveryInfoDto deliveryInfoDto;

    private boolean deliveryInfoSelected = false;

    private OrderType orderType;

    private OrderStatus orderStatus;
//
    private Double totalPrice = 0.0D;

}
