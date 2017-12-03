package pl.tu.kielce.pizza.fe.order.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;
import pl.tu.kielce.pizza.common.order.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class UserOrderController {

    @Autowired
    private final OrderService orderService;

    @GetMapping("waitingForApproval")
    public String findAllWaitingForApproval(Model model) {
        List<UserOrderDto> userOrderDtos = orderService.findAllWaitingForApproval();
        model.addAttribute("userOrderDtos", userOrderDtos);
        return "order/waiting_for_approval";
    }

    @GetMapping("inProgress")
    public String findAllInProgress(Model model) {
        List<UserOrderDto> userOrderDtos = orderService.findAllInProgress();
        model.addAttribute("userOrderDtos", userOrderDtos);
        return "order/waiting_for_approval";
    }

    @GetMapping("duringDelivery")
    public String findAllDuringDelivery(Model model) {
        List<UserOrderDto> userOrderDtos = orderService.findAllDuringDelivery();
        model.addAttribute("userOrderDtos", userOrderDtos);
        return "order/waiting_for_approval";
    }


}
