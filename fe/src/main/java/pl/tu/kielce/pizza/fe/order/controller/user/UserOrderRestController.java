package pl.tu.kielce.pizza.fe.order.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tu.kielce.pizza.common.order.service.OrderService;

@RestController
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class UserOrderRestController {

    @Autowired
    private final OrderService orderService;

    @GetMapping("accept/{orderId}")
    public ResponseEntity<Object> accept(@PathVariable("orderId") Long orderId) {
        orderService.acceptOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("orderInTransport/{orderId}")
    public ResponseEntity<Object> orderInTransport(@PathVariable("orderId") Long orderId) {
        orderService.orderInTransport(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("orderFinished/{orderId}")
    public ResponseEntity<Object> orderFinished(@PathVariable("orderId") Long orderId) {
        orderService.orderFinished(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
