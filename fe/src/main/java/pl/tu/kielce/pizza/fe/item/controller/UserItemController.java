package pl.tu.kielce.pizza.fe.item.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/user/item")
@RequiredArgsConstructor
public class UserItemController {


    @Autowired
    private final ItemService itemService;

    @GetMapping("{itemId}")
    public String findOne(
            Model model,
            @PathVariable("itemId") Long itemId) {

        ItemDto itemDto = itemService.findOne(itemId);

        model.addAttribute("itemDto", itemDto);
        return "item/item_show";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public String allItems(Model model) {
//        List<ItemDto> items = itemService.findAll();
        List<ItemDto> items = itemService.findAllWithMultiplier();
        model.addAttribute("items", items);
        return "item/item_search";
    }

}
