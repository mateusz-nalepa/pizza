package pl.tu.kielce.pizza.be.order.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.common.mapper.CommonMapper;
import pl.tu.kielce.pizza.be.department.repository.DepartmentRepository;
import pl.tu.kielce.pizza.be.item.model.jpa.Item;
import pl.tu.kielce.pizza.be.item.repository.ItemRepository;
import pl.tu.kielce.pizza.be.order.mapper.OrderMapper;
import pl.tu.kielce.pizza.be.order.model.jpa.BoughtItem;
import pl.tu.kielce.pizza.be.order.model.jpa.BoughtPizza;
import pl.tu.kielce.pizza.be.order.model.jpa.Order;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.be.pizza.repository.PizzaRepository;
import pl.tu.kielce.pizza.be.security.repository.user.UserRepository;
import pl.tu.kielce.pizza.common.common.enums.OrderStatus;
import pl.tu.kielce.pizza.common.common.enums.OrderType;
import pl.tu.kielce.pizza.common.order.dto.BoughtItemDto;
import pl.tu.kielce.pizza.common.order.dto.BoughtPizzaDto;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderExecutor {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final PizzaRepository pizzaRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final CommonMapper commonMapper;

    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    private final OrderMapper orderMapper;

    @Autowired
    private final UserContext userContext;

    public UserOrderDto submitOrder(UserOrderDto userOrderDto) {


        Order order = new Order();

        Double totalPrice = userOrderDto.getTotalPrice();

        //TODO weź department z kontekstu jak jest anonymouseUser!

        Long departmentId = userContext.fetchDepartment().getId();

        order.setDepartment(departmentRepository.findOne(departmentId));

        order.setBoughtPizzas(fetchPizzas(userOrderDto.getBoughtPizzas(), order));
        order.setBoughtItems(fetchItems(userOrderDto.getBoughtItems(), order));
        order.setDeliveryInfo(orderMapper.mapDtoToEntity(userOrderDto.getDeliveryInfoDto()));
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.WAITING_FOR_APPROVAL);

        order.setOrderType(OrderType.DELIVERY);
        fetchBuyer(order);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.entityToDto(savedOrder);


    }

    private void fetchBuyer(Order order) {

        if (UserUtils.isLogged()) {
            Optional<UserDto> userDtoOptional = UserUtils.getUserDto();
            if (userDtoOptional.isPresent()) {
                Long userId = userDtoOptional.get().getId();
                if (UserUtils.isClient() && !UserUtils.isManager()) {
                    order.setBuyer(userRepository.findOne(userId));
                }
//                if (UserUtils.isManager()) {
//                    order.setUser(userRepository.findOne(userId));
//                }
            }
        }

    }


    private List<BoughtItem> fetchItems(List<BoughtItemDto> boughtItems, Order order) {
        return boughtItems
                .stream()
                .map(boughtItemDto -> fetchSingleItem(boughtItemDto, order))
                .collect(Collectors.toList());
    }

    private List<BoughtPizza> fetchPizzas(List<BoughtPizzaDto> boughtPizzas, Order order) {


        return boughtPizzas
                .stream()
                .map(boughtPizzaDto -> fetchSinglePizza(boughtPizzaDto, order))
                .collect(Collectors.toList());

    }

    private BoughtPizza fetchSinglePizza(BoughtPizzaDto boughtPizzaDto, Order order) {

        BoughtPizza boughtPizza = new BoughtPizza();
        boughtPizza.setOrder(order);

        Pizza pizza = pizzaRepository.findOne(boughtPizzaDto.getPizza().getId());

        boughtPizza.setPizza(pizza);
        boughtPizza.setPurchasePrice(boughtPizzaDto.getPurchasePrice());
        boughtPizza.setPizzaType(boughtPizzaDto.getPizzaType());
        boughtPizza.setQuantity(boughtPizzaDto.getQuantity());

        return boughtPizza;
    }

    private BoughtItem fetchSingleItem(BoughtItemDto boughtItemDto, Order order) {

        BoughtItem boughtItem = new BoughtItem();
        boughtItem.setOrder(order);

        Item item = itemRepository.findOne(boughtItemDto.getItem().getId());

        boughtItem.setItem(item);
        boughtItem.setPurchasePrice(boughtItemDto.getPurchasePrice());
//        boughtItem.setPizzaType(boughtPizzaDto.getPizzaType());
        boughtItem.setQuantity(boughtItemDto.getQuantity());

        return boughtItem;
    }


    public List<UserOrderDto> findAllWaitingForApproval() {

        Long departmentId = UserUtils.departmentId();
        return orderRepository
//                .findAllByOrderStatus(OrderStatus.WAITING_FOR_APPROVAL)
                .findAllWaitingForApprovalInGivenDepartment(departmentId)
                .stream()
                .map(orderMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public void acceptOrder(Long orderId) {
        orderRepository.setOrderAsAccepted(orderId);
    }

    public void orderInTransport(Long orderId) {
        orderRepository.setOrderAsDuringDelivery(orderId);
    }

    public void orderFinished(Long orderId) {
        orderRepository.setOrderAsDone(orderId);
    }

    public List<UserOrderDto> findAllInProgress() {
        Long departmentId = UserUtils.departmentId();
        return orderRepository
//                .findAllByOrderStatus(OrderStatus.WAITING_FOR_APPROVAL)
                .findAllInProgress(departmentId)
                .stream()
                .map(orderMapper::entityToDto)
                .collect(Collectors.toList());

    }

    public List<UserOrderDto> findAllDuringDelivery() {
        Long departmentId = UserUtils.departmentId();
        return orderRepository
//                .findAllByOrderStatus(OrderStatus.WAITING_FOR_APPROVAL)
                .findAllDuringDelivery(departmentId)
                .stream()
                .map(orderMapper::entityToDto)
                .collect(Collectors.toList());

    }

    public List<UserOrderDto> findAllOrdersForActualLogedUser() {
        Long userId = UserUtils.getUserId();
        return orderRepository
//                .findAllByOrderStatus(OrderStatus.WAITING_FOR_APPROVAL)
                .findAllOrdersForActualLogedUser(userId)
                .stream()
                .map(orderMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public UserOrderDto findOne(Long orderId) {
        Order one = orderRepository.findOne(orderId);
        return orderMapper.entityToDto(one);
    }
}
