package pl.tu.kielce.pizza.fe.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tu.kielce.pizza.common.order.dto.AddItemDto;
import pl.tu.kielce.pizza.common.order.dto.AddPizzaDto;
import pl.tu.kielce.pizza.common.order.service.OrderService;

@RestController
@RequestMapping("/client/order")
@RequiredArgsConstructor
public class OrderRestController {


    @Autowired
    private final OrderService orderService;

    @PostMapping("/addPizza")
    public ResponseEntity<?> addPizzaToOrder(@RequestBody AddPizzaDto addPizzaDto) {

        orderService.addPizzaToOrder(addPizzaDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToOrder(@RequestBody AddItemDto addItemDto) {

        orderService.addItemToOrder(addItemDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/aaa")
    public String asd() {
        return "aaa";
    }

}
