package pl.tu.kielce.pizza.common.order.service;

import pl.tu.kielce.pizza.common.order.dto.AddItemDto;
import pl.tu.kielce.pizza.common.order.dto.AddPizzaDto;
import pl.tu.kielce.pizza.common.order.dto.DeliveryInfoDto;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;

import java.util.List;

public interface OrderService {

    void addPizzaToOrder(AddPizzaDto addPizzaDto);

    void addItemToOrder(AddItemDto addItemDto);

    UserOrderDto getUserContext();

    void setDeliveryData(DeliveryInfoDto deliveryInfoDto);

    void submitOrder();

    List<UserOrderDto> findAllWaitingForApproval();

    void acceptOrder(Long orderId);

    void orderInTransport(Long orderId);

    void orderFinished(Long orderId);

    List<UserOrderDto> findAllInProgress();

    List<UserOrderDto> findAllDuringDelivery();

    List<UserOrderDto> findAllOrdersForActualLogedUser();

    UserOrderDto findOne();

}
