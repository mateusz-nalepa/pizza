package pl.tu.kielce.pizza;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.tu.kielce.pizza.be.order.model.jpa.Order;
import pl.tu.kielce.pizza.be.order.repository.OrderRepository;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;
import pl.tu.kielce.pizza.be.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.enums.OrderType;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.common.ingredient.service.IngredientService;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;
import pl.tu.kielce.pizza.common.item.service.ItemService;
import pl.tu.kielce.pizza.common.pizza.dto.PizzaDto;
import pl.tu.kielce.pizza.common.pizza.service.PizzaService;
import pl.tu.kielce.pizza.common.security.dto.AccountStatus;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RunAtStart {

    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final IngredientService ingredientService;


    @Autowired
    private final PizzaService pizzaService;

    @PostConstruct
    public void runAtStart() {
        defaultRoles();
        defaultUsers();
        defaultDepartment();
        findAllDepartment();
        defaultItems();
        List<IngredientDto> defaultingredients = defaultingredients();
        saveDefaultPizza(defaultingredients);

        defaultOrder();
        System.out.println("ZAPISANO DANE");
    }

    private void saveDefaultPizza(List<IngredientDto> defaultingredients) {

        PizzaDto pizzaDto = PizzaDto
                .builder()
                .name("Margharita")
                .description("Margharita description")
                .price(25.0D)
                .ingredients(defaultingredients)
                .build();

        pizzaService.create(pizzaDto);
    }

    private void defaultOrder() {

        Order order = new Order();
        order.setTotalPrice(20D);
        order.setOrderType(OrderType.DELIVERY);

        orderRepository.save(order);
    }

    private void defaultItems() {
        ItemDto itemDto = ItemDto.builder().name("NAME 1").price(10.0D).description("DESC 1").build();
        itemService.create(itemDto);

        itemDto = ItemDto.builder().name("NAME 2").description("DESC 2").price(20.0D).build();
        itemService.create(itemDto);

        itemDto = ItemDto.builder().name("NAME 3").description("DESC 3").price(30.0D).build();
        itemService.create(itemDto);
    }

    private List<IngredientDto> defaultingredients() {

        List<IngredientDto> ingredientDtoList = new ArrayList<>();

        IngredientDto ingredientDto = IngredientDto.builder().name("NAME 1").selected(true).description("DESC 1").build();

        ingredientService.create(ingredientDto);
        ingredientDto.setId(1L);
        ingredientDtoList.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("NAME 2").description("DESC 2").selected(true).build();
        ingredientService.create(ingredientDto);
        ingredientDto.setId(2L);
        ingredientDtoList.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("NAME 3").description("DESC 3").build();
        ingredientService.create(ingredientDto);

        return ingredientDtoList;


    }

    private void findAllDepartment() {
        departmentService.findAll();
    }

    private void defaultDepartment() {
        UserDto manager = new UserDto();
        manager.setId(1L);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setAddressDto(defaultAddress());
        departmentDto.setManager(manager);
        departmentDto.setMultiplier(0.10D);
        departmentDto.setActive(true);
        departmentService.create(departmentDto);
    }

    private AddressDto defaultAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("CITY");
        addressDto.setStreet("STREET");
        addressDto.setHouseNumber(1);
        addressDto.setFlatNumber(1);
        return addressDto;
    }

    private void defaultUsers() {
        UserDto userDto = new UserDto();
        userDto.setName("HODOREK");
        userDto.setEmail("admin@pizza.pl");
        userDto.setName("Mateusz");
        userDto.setLastName("Nalepa");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setAccountStatus(AccountStatus.ACTIVE);
//        userDto.setAccountStatus(AccountStatus.INITIAL);

        List<RoleDto> roleDtos = new ArrayList<>();
        roleDtos.add(RoleDto.builder().id(1L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(2L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());

        userDto.setRoles(roleDtos);
        userService.saveUser(userDto);

        userDto = new UserDto();
        userDto.setName("DWA");
        userDto.setEmail("manager@pizza.pl");
        userDto.setName("NAME DWA");
        userDto.setLastName("NAZWISKO DWA");
        userDto.setPassword("asd123");
        userDto.setActive(true);

        roleDtos.add(RoleDto.builder().id(2L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        userDto.setRoles(roleDtos);

        userService.saveUser(userDto);
    }

    private void defaultRoles() {
        Role adminRole = Role.builder().role("ADMIN").build();
        roleRepository.save(adminRole);

        Role managerRole = Role.builder().role("MANAGER").build();
        roleRepository.save(managerRole);

        Role userRole = Role.builder().role("USER").build();
        roleRepository.save(userRole);

        Role clientRole = Role.builder().role("CLIENT").build();
        roleRepository.save(clientRole);
    }



}
