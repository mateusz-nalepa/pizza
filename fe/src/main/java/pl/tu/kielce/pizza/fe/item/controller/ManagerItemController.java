package pl.tu.kielce.pizza.fe.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager/item")
@RequiredArgsConstructor
public class ManagerItemController {

    @Autowired
    private final ItemService itemService;

    @GetMapping("/")
    public String itemDefinition(Model model) {
        ItemDto itemDto = new ItemDto();
        model.addAttribute("itemDto", itemDto);
        return "item/item_definition";
    }

    @PostMapping("/")
    public String addNewItem(@Valid ItemDto itemDto, BindingResult bindingResult, Model model) {

        itemDto = itemService.create(itemDto);
        return "redirect:/user/item/" + itemDto.getId();
    }

}
