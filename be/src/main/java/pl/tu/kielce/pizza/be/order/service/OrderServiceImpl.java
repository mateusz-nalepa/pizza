package pl.tu.kielce.pizza.be.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.be.order.session.UserOrderInfo;
import pl.tu.kielce.pizza.common.common.enums.PizzaType;
import pl.tu.kielce.pizza.common.common.util.PriceUtils;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;
import pl.tu.kielce.pizza.common.order.dto.AddItemDto;
import pl.tu.kielce.pizza.common.order.dto.AddPizzaDto;
import pl.tu.kielce.pizza.common.order.dto.BoughtItemDto;
import pl.tu.kielce.pizza.common.order.dto.BoughtPizzaDto;
import pl.tu.kielce.pizza.common.order.service.OrderService;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final PizzaService pizzaService;

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final UserOrderInfo userOrderInfo;

    @Override
    public void addPizzaToOrder(AddPizzaDto addPizzaDto) {

        PizzaType pizzaType = PizzaType.fromId(addPizzaDto.getPizzaType());
        PizzaDto pizzaDto = pizzaService.findOne(addPizzaDto.getPizzaId());

        BoughtPizzaDto boughtPizzaDto = BoughtPizzaDto
                .builder()
                .pizzaType(pizzaType)
                .pizza(pizzaDto)
                .build();

        setPizzaPrice(boughtPizzaDto);
        userOrderInfo.addPizzaToOrder(boughtPizzaDto);
    }

    @Override
    public void addItemToOrder(AddItemDto addItemDto) {
        ItemDto itemDto = itemService.findOne(addItemDto.getItemId());

        BoughtItemDto boughtItemDto = BoughtItemDto
                .builder()
                .item(itemDto)
                .purchasePrice(itemDto.getPrice())
                .build();

        userOrderInfo.addItemToOrder(boughtItemDto);

        System.out.println("An item was added!");

    }

    private void setPizzaPrice(BoughtPizzaDto boughtPizzaDto) {
        PizzaType pizzaType = boughtPizzaDto.getPizzaType();
        PizzaDto pizzaDto = boughtPizzaDto.getPizza();

        boughtPizzaDto.setPurchasePrice(pizzaDto.getPrice());

        if (pizzaType.equals(PizzaType.MEDIUM)) {
            boughtPizzaDto.setPurchasePrice(PriceUtils.mediumPrice(pizzaDto.getPrice()));
        } else if (pizzaType.equals(PizzaType.LARGE)) {
            boughtPizzaDto.setPurchasePrice(PriceUtils.largePrice(pizzaDto.getPrice()));
        }
    }
}
