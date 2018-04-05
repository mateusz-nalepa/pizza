package pl.tu.kielce.pizza.be.order.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.common.mapper.CommonMapper;
import pl.tu.kielce.pizza.be.item.mapper.ItemMapper;
import pl.tu.kielce.pizza.be.order.model.jpa.BoughtItem;
import pl.tu.kielce.pizza.be.order.model.jpa.BoughtPizza;
import pl.tu.kielce.pizza.be.order.model.jpa.DeliveryInfo;
import pl.tu.kielce.pizza.be.order.model.jpa.Order;
import pl.tu.kielce.pizza.be.pizza.mapper.PizzaMapper;
import pl.tu.kielce.pizza.be.security.mapper.UserMapper;
import pl.tu.kielce.pizza.common.order.dto.BoughtItemDto;
import pl.tu.kielce.pizza.common.order.dto.BoughtPizzaDto;
import pl.tu.kielce.pizza.common.order.dto.DeliveryInfoDto;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {


    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final ItemMapper itemMapper;

    @Autowired
    private final CommonMapper commonMapper;

    @Autowired
    private final PizzaMapper pizzaMapper;

    public UserOrderDto entityToDto(Order order) {

        UserOrderDto userOrderDto = new UserOrderDto();
        userOrderDto.setId(order.getId());
        

        userOrderDto.setBoughtItems(fetchItems(order.getBoughtItems()));
        userOrderDto.setBoughtPizzas(fetchPizzas(order.getBoughtPizzas()));
        userOrderDto.setOrderType(order.getOrderType());
        userOrderDto.setOrderStatus(order.getOrderStatus());
        userOrderDto.setTotalPrice(order.getTotalPrice());
        userOrderDto.setDeliveryInfoDto(mapEntityToDto(order.getDeliveryInfo()));
        userOrderDto = commonMapper.baseEntityToDto(order, userOrderDto);
        return userOrderDto;
    }

    public DeliveryInfoDto mapEntityToDto(DeliveryInfo deliveryInfo) {

        DeliveryInfoDto deliveryInfoDto = new DeliveryInfoDto();
        deliveryInfoDto.setAddressDto(commonMapper.addressEntityToDto(deliveryInfo.getAddress()));
        deliveryInfoDto.setNote(deliveryInfo.getNote());
        deliveryInfoDto.setDeliveryUserDataDto(commonMapper.mapDeliveryUserDataToDto(deliveryInfo.getDeliveryUserData()));

        return deliveryInfoDto;
    }


    public List<BoughtPizzaDto> fetchPizzas(List<BoughtPizza> boughtPizzas) {
        return boughtPizzas
                .stream()
                .map(this::boughtPizzaEntityToDto)
                .collect(Collectors.toList());
    }

    private BoughtPizzaDto boughtPizzaEntityToDto(BoughtPizza boughtPizza) {
        BoughtPizzaDto boughtItemDto = new BoughtPizzaDto();
        boughtItemDto.setQuantity(boughtPizza.getQuantity());
        boughtItemDto.setPurchasePrice(boughtPizza.getPurchasePrice());
        boughtItemDto.setPizza(pizzaMapper.entityToDto(boughtPizza.getPizza()));
        boughtItemDto.setPizzaType(boughtPizza.getPizzaType());
        return boughtItemDto;
    }

    private List<BoughtItemDto> fetchItems(List<BoughtItem> boughtItems) {

        return boughtItems
                .stream()
                .map(this::boughtItemEntityToDto)
                .collect(Collectors.toList());
    }

    private BoughtItemDto boughtItemEntityToDto(BoughtItem boughtItem) {

        BoughtItemDto boughtItemDto = new BoughtItemDto();
        boughtItemDto.setQuantity(boughtItem.getQuantity());
        boughtItemDto.setPurchasePrice(boughtItem.getPurchasePrice());
        boughtItemDto.setItem(itemMapper.entityToDto(boughtItem.getItem()));

        return boughtItemDto;
    }


    public DeliveryInfo mapDtoToEntity(DeliveryInfoDto dto) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();

        deliveryInfo.setNote(dto.getNote());
        deliveryInfo.setAddress(commonMapper.addressDtoToEntity(dto.getAddressDto()));
        deliveryInfo.setDeliveryUserData(commonMapper.mapDeliveryUserDataToEntity(dto.getDeliveryUserDataDto()));

        return deliveryInfo;
    }
}
