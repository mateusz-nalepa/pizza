package pl.tu.kielce.pizza.fe.order.controller.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;
import pl.tu.kielce.pizza.common.messages.MessageSourceAccessor;
import pl.tu.kielce.pizza.common.order.dto.DeliveryInfoDto;
import pl.tu.kielce.pizza.common.order.dto.DeliveryUserDataDto;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;
import pl.tu.kielce.pizza.common.order.service.OrderService;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client/order")
@RequiredArgsConstructor
public class ClientOrderController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final PizzaService pizzaService;

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    private final UserContext userContext;

    @GetMapping
    public String newOrder(Model model) {

        DepartmentDto departmentDto = userContext.fetchDepartment();

        if (departmentDto == null) {
            if (userContext.getDepartmentDto() == null) {
                return "redirect:/client/department/";
            }
        }



        List<PizzaDto> pizzas = pizzaService.findAllWithMultiplier();
        List<ItemDto> items = itemService.findAllWithMultiplier();
        DeliveryInfoDto deliveryInfoDto = new DeliveryInfoDto();


        Optional<UserDto> userDtoOptional = UserUtils.getUserDto();

        if (userDtoOptional.isPresent()) {



            UserDto userDto = userDtoOptional.get();


            if (userContext.getUserOrderDto().isDeliveryInfoSelected()) {
                deliveryInfoDto = userContext.getUserOrderDto().getDeliveryInfoDto();
            } else {
                deliveryInfoDto.setAddressDto(userDto.getAddressDto());
                deliveryInfoDto.setDeliveryUserDataDto(fetchUserData(userDto));
            }

        }


        model.addAttribute("boughtPizzas", pizzas);
        model.addAttribute("items", items);
        model.addAttribute("deliveryInfoDto", deliveryInfoDto);
        return "order/new_order";
    }

    @GetMapping("/summary")
    public String orderInfo(Model model) {
        UserOrderDto userOrderDto = orderService.getUserContext();
        model.addAttribute("userOrderDto", userOrderDto);
        return "order/order_info";
    }

    @PostMapping("/addAddress")
    public String addAddress(@Valid DeliveryInfoDto deliveryInfoDto, BindingResult bindingResult, Model model) {

//        if (bindingResult.hasErrors()) {
//            return "department/department_definition";
//        }

        orderService.setDeliveryData(deliveryInfoDto);
        String message = messageSourceAccessor.getMessage("order.address.added");

        return "redirect:/client/order/summary?address=" + message;
    }

    @GetMapping("/submitOrder")
    public String submitOrder() {

        orderService.submitOrder();
        return "redirect:/client/order/submitted";
    }

    @GetMapping("/submitted")
    public String orderSubmitted(Model model) {

        model.addAttribute("submitted", true);
        UserOrderDto userOrderDto = orderService.getUserContext();
        model.addAttribute("userOrderDto", userOrderDto);
        return "order/order_info";

    }

    private DeliveryUserDataDto fetchUserData(UserDto userDto) {
        return DeliveryUserDataDto
                .builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }
}
