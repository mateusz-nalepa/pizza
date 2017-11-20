package pl.tu.kielce.pizza.fe.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;

import java.util.List;

@Controller
@RequestMapping("/client/order")
@RequiredArgsConstructor
public class OrderController {


    @Autowired
    private final PizzaService pizzaService;

    @Autowired
    private final ItemService itemService;

    @GetMapping
    public String newOrder(Model model) {
        List<PizzaDto> pizzas = pizzaService.findAllWithMultiplier();
        List<ItemDto> items = itemService.findAllWithMultiplier();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("items", items);
        return "order/new_order";
    }

}
