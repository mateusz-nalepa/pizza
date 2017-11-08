package pl.tu.kielce.pizza.ingredient;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.ingredient.dto.ItemDto;
import pl.tu.kielce.pizza.ingredient.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager/item")
@RequiredArgsConstructor
public class ItemController {


    @Autowired
    private final ItemService itemService;

    @GetMapping("{itemId}")
    public String getItemById(
            Model model,
            @PathVariable("itemId") Long itemId) {

        ItemDto itemDto = itemService.getById(itemId);

        model.addAttribute("itemDto", itemDto);
        return "item/item_show";
    }

    @GetMapping("/")
    public String itemDefinition(Model model) {
        ItemDto itemDto = new ItemDto();
        model.addAttribute("itemDto", itemDto);
        return "item/item_definition";
    }

    @PostMapping("/")
    public String addNewItem(@Valid ItemDto itemDto, BindingResult bindingResult, Model model) {

        itemDto = itemService.add(itemDto);
        System.out.println("hodoreczek");

        return "redirect:/manager/item/" + itemDto.getId();
    }

    @GetMapping("/all")
    public String allItem(Model model) {
        List<ItemDto> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/item_search";
    }

}
