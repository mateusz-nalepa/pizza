package pl.tu.kielce.pizza.be.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.order.repository.OrderExecutor;
import pl.tu.kielce.pizza.common.common.enums.PizzaType;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;
import pl.tu.kielce.pizza.common.order.dto.*;
import pl.tu.kielce.pizza.common.order.service.OrderService;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final PizzaService pizzaService;

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final UserContext userContext;

    @Autowired
    private final OrderExecutor orderExecutor;

    @Override
    public void addPizzaToOrder(AddPizzaDto addPizzaDto) {

        PizzaType pizzaType = PizzaType.fromId(addPizzaDto.getPizzaType());


        PizzaDto pizzaDto = pizzaService.findOne(addPizzaDto.getPizzaId());

        if (pizzaExistInOrder(pizzaDto, pizzaType)) {
            System.out.println("istnieje na liscie");
            for (BoughtPizzaDto boughtPizzaDto : userContext.getUserOrderDto().getBoughtPizzas()) {
                if (isPizzaOnListv2(boughtPizzaDto, pizzaDto, pizzaType)) {
                    userContext.addPizzaTotalPrice(boughtPizzaDto);
                    break;

                }
            }
        } else {
            BoughtPizzaDto boughtPizzaDto = BoughtPizzaDto
                    .builder()
                    .pizzaType(pizzaType)
                    .pizza(pizzaDto)
                    .build();

            setPizzaPrice(boughtPizzaDto);
            userContext.addPizzaToOrder(boughtPizzaDto);
        }

    }

    private boolean pizzaExistInOrder(PizzaDto pizzaDto, PizzaType pizzaType) {
        long count = userContext
                .getUserOrderDto()
                .getBoughtPizzas()
                .stream()
                .filter(boughtPizzaDto -> isPizzaOnList(boughtPizzaDto, pizzaDto, pizzaType))
                .count();

        return count >= 1;
    }

    private boolean isPizzaOnList(BoughtPizzaDto boughtPizzaDto, PizzaDto pizzaDto,  PizzaType pizzaType) {
        PizzaDto boughtPizza = boughtPizzaDto.getPizza();

//        if (boughtPizzaDto.getPizzaType().equals(PizzaType.MEDIUM)) {
        if (pizzaType.equals(PizzaType.MEDIUM)) {
            pizzaDto.setPrice(NewPriceContextUtils.mediumPrice(pizzaDto.getPrice()));
//        } else if (boughtPizzaDto.getPizzaType().equals(PizzaType.LARGE)) {
        } else if (pizzaType.equals(PizzaType.LARGE)) {
            pizzaDto.setPrice(NewPriceContextUtils.largePrice(pizzaDto.getPrice()));
        }

        if (boughtPizza.getId().equals(pizzaDto.getId())) {
            if (boughtPizzaDto.getPurchasePrice().equals(pizzaDto.getPrice())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPizzaOnListv2(BoughtPizzaDto boughtPizzaDto, PizzaDto pizzaDto,  PizzaType pizzaType) {
        PizzaDto boughtPizza = boughtPizzaDto.getPizza();

////        if (boughtPizzaDto.getPizzaType().equals(PizzaType.MEDIUM)) {
//        if (pizzaType.equals(PizzaType.MEDIUM)) {
//            pizzaDto.setPrice(NewPriceContextUtils.mediumPrice(pizzaDto.getPrice()));
////        } else if (boughtPizzaDto.getPizzaType().equals(PizzaType.LARGE)) {
//        } else if (pizzaType.equals(PizzaType.LARGE)) {
//            pizzaDto.setPrice(NewPriceContextUtils.largePrice(pizzaDto.getPrice()));
//        }

        if (boughtPizza.getId().equals(pizzaDto.getId())) {
            if (boughtPizzaDto.getPurchasePrice().equals(pizzaDto.getPrice())) {
                return true;
            }
        }
        return false;
    }

    private boolean isItemOnList(BoughtItemDto boughtItemDto, ItemDto itemDto) {
        if (boughtItemDto.getItem().getId().equals(itemDto.getId())) {
            if (boughtItemDto.getItem().getPrice().equals(itemDto.getPrice())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addItemToOrder(AddItemDto addItemDto) {
        ItemDto itemDto = itemService.findOne(addItemDto.getItemId());

        if (itemExistInOrder(itemDto)) {

            for (BoughtItemDto boughtItemDto : userContext.getUserOrderDto().getBoughtItems()) {
                if (isItemOnList(boughtItemDto, itemDto)) {
                    userContext.addItemTotalPrice(boughtItemDto);
                    break;
                }
            }
        } else {
            BoughtItemDto boughtItemDto = BoughtItemDto
                    .builder()
                    .item(itemDto)
                    .purchasePrice(itemDto.getPrice())
                    .build();

            userContext.addItemToOrder(boughtItemDto);
        }

    }

    private boolean itemExistInOrder(ItemDto itemDto) {
        long count = userContext
                .getUserOrderDto()
                .getBoughtItems()
                .stream()
                .filter(boughtItemDto -> isItemOnList(boughtItemDto, itemDto))
                .count();

        return count >= 1;
    }

    @Override
    public UserOrderDto getUserContext() {
        return userContext.getUserOrderDto();
    }

    @Override
    public void setDeliveryData(DeliveryInfoDto deliveryInfoDto) {
        userContext.getUserOrderDto().setDeliveryInfoDto(deliveryInfoDto);
        userContext.getUserOrderDto().setDeliveryInfoSelected(true);
    }

    @Override
    @Transactional
    public void submitOrder() {
//        String note = userContext.getUserOrderDto().getAddressDto().getNote();
//        userContext.getUserOrderDto().setNote(note);
        UserOrderDto userOrderDto = orderExecutor.submitOrder(userContext.getUserOrderDto());
        UserOrderDto newUserOrderDto = new UserOrderDto();
        newUserOrderDto.setId(userOrderDto.getId());
        userContext.setUserOrderDto(newUserOrderDto);

    }

    @Override
    public List<UserOrderDto> findAllWaitingForApproval() {

        return orderExecutor.findAllWaitingForApproval();
    }

    @Override
    @Transactional
    public void acceptOrder(Long orderId) {
        orderExecutor.acceptOrder(orderId);
    }

    @Override
    @Transactional
    public void orderInTransport(Long orderId) {
        orderExecutor.orderInTransport(orderId);
    }

    @Override
    @Transactional
    public void orderFinished(Long orderId) {
        orderExecutor.orderFinished(orderId);
    }

    @Override
    public List<UserOrderDto> findAllInProgress() {
        return orderExecutor.findAllInProgress();
    }

    @Override
    public List<UserOrderDto> findAllDuringDelivery() {
        return orderExecutor.findAllDuringDelivery();
    }

    @Override
    public List<UserOrderDto> findAllOrdersForActualLogedUser() {
        return orderExecutor.findAllOrdersForActualLogedUser();

    }

    @Override
    public UserOrderDto findOne() {

        Long orderId = userContext.getUserOrderDto().getId();

        if (orderId == null) {
            return null;
        }

        return orderExecutor.findOne(orderId);
    }

    @Override
    public void deletePizza(Long pizzaId, Double pizzaPrice) {

        for (BoughtPizzaDto boughtPizzaDto: userContext.getUserOrderDto().getBoughtPizzas()) {

            if ( (boughtPizzaDto.getPizza().getId().equals(pizzaId)) && boughtPizzaDto.getPurchasePrice().equals(pizzaPrice)) {
                boughtPizzaDto.decreaseQuantity();
                Double oldTotalPrice = userContext.getUserOrderDto().getTotalPrice();
                Double newTotalPrice = oldTotalPrice - pizzaPrice;
                userContext.getUserOrderDto().setTotalPrice(newTotalPrice);

            }

        }

        userContext
                .getUserOrderDto()
                .getBoughtPizzas()
                .removeIf(boughtPizzaDto -> boughtPizzaDto.getQuantity().equals(0));

    }

    @Override
    public void deleteItem(Long itemId, Double itemPrice) {
        for (BoughtItemDto boughtItemDto: userContext.getUserOrderDto().getBoughtItems()) {
            if ( (boughtItemDto.getItem().getId().equals(itemId)) ) {//&& boughtPizzaDto.getPurchasePrice().equals(pizzaPrice)) {
                boughtItemDto.decreaseQuantity();
                Double oldTotalPrice = userContext.getUserOrderDto().getTotalPrice();
                Double newTotalPrice = oldTotalPrice - itemPrice;
                userContext.getUserOrderDto().setTotalPrice(newTotalPrice);
            }
        }

        userContext
                .getUserOrderDto()
                .getBoughtItems()
                .removeIf(boughtItemDto -> boughtItemDto.getQuantity().equals(0));
    }

    private void setPizzaPrice(BoughtPizzaDto boughtPizzaDto) {
        PizzaType pizzaType = boughtPizzaDto.getPizzaType();
        PizzaDto pizzaDto = boughtPizzaDto.getPizza();

        boughtPizzaDto.setPurchasePrice(pizzaDto.getPrice());

        if (pizzaType.equals(PizzaType.MEDIUM)) {
            boughtPizzaDto.setPurchasePrice(NewPriceContextUtils.mediumPrice(pizzaDto.getPrice()));
        } else if (pizzaType.equals(PizzaType.LARGE)) {
            boughtPizzaDto.setPurchasePrice(NewPriceContextUtils.largePrice(pizzaDto.getPrice()));
        }
    }
}
