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
import pl.tu.kielce.pizza.common.security.enums.MainRoleType;
import pl.tu.kielce.pizza.common.security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RunAtStart {

    private static Integer number = 0;

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

//        defaultOrder();
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
        ItemDto itemDto = ItemDto.builder().name("Cola").price(10.0D).description("Z colą każda pizza smakuje lepiej!").build();
        itemService.create(itemDto);

        itemDto = ItemDto.builder().name("Sok multiwitaminowy").description("Z sokiem wszystko jest lepsze!").price(20.0D).build();
        itemService.create(itemDto);

        itemDto = ItemDto.builder().name("Gorąca czekolada").description("Na chłodne dni").price(30.0D).build();
        itemService.create(itemDto);
    }

    private List<IngredientDto> defaultingredients() {

        List<IngredientDto> ingredientDtoList = new ArrayList<>();

        IngredientDto ingredientDto = IngredientDto.builder().name("Szynka").selected(true).description("Szynkowy składnik").build();

        ingredientService.create(ingredientDto);
        ingredientDto.setId(1L);
        ingredientDtoList.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("Ser").description("Dzięki niemu pizza jest się ciągnie").selected(true).build();
        ingredientService.create(ingredientDto);
        ingredientDto.setId(2L);
        ingredientDtoList.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("Sos pomidorowy").description("Podstawowy składnik każdej pizzy").build();
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

        ///////////////////////////////////////////////////////////////////

        manager = new UserDto();
        manager.setId(2L);
        departmentDto = new DepartmentDto();
        departmentDto.setAddressDto(defaultAddress());
        departmentDto.setManager(manager);
        departmentDto.setMultiplier(0.20D);
        departmentDto.setActive(true);
        departmentService.create(departmentDto);


        UserDto byEmail = userService.findByEmail("manager@pizza.pl");


    }

    private AddressDto defaultAddress() {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Kielce " + number);
        number++;
        addressDto.setStreet("Tysiąclecia P.P");
        addressDto.setHouseNumber(1);
        addressDto.setFlatNumber(1);
//        addressDto.setEmail("order@email.pl");
//        addressDto.setPhoneNumber("123123123");
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
        userDto.setMainRoleType(MainRoleType.ADMIN);
        userDto.setAccountStatus(AccountStatus.ACTIVE);
        userDto.setAddressDto(defaultAddress());
        userDto.setPhoneNumber("111111111");
//        userDto.setAccountStatus(AccountStatus.INITIAL);

        List<RoleDto> roleDtos = new ArrayList<>();
        roleDtos.add(RoleDto.builder().id(1L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(2L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());

        userDto.setRoles(roleDtos);
        userService.saveUser(userDto);
//////////////////////////////////////////////////////////////////////////////////////
        userDto = new UserDto();
        userDto.setMainRoleType(MainRoleType.MANAGER);
        userDto.setPhoneNumber("222222222");
        userDto.setName("DWA");
        userDto.setEmail("manager@pizza.pl");
        userDto.setName("NAME DWA");
        userDto.setLastName("NAZWISKO DWA");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setAddressDto(defaultAddress());

        roleDtos = new ArrayList<>();
        roleDtos.add(RoleDto.builder().id(2L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());
        userDto.setRoles(roleDtos);
        userDto.setAccountStatus(AccountStatus.ACTIVE);

        userService.saveUser(userDto);
//////////////////////////////////////////////////////////////////////////////////////

        userDto = new UserDto();
        userDto.setMainRoleType(MainRoleType.USER);
        userDto.setPhoneNumber("333333333");
        userDto.setName("TRZY");
        userDto.setEmail("user1@pizza.pl");
        userDto.setName("NAME TRZY");
        userDto.setLastName("NAZWISKO TRZY");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setAddressDto(defaultAddress());

        roleDtos = new ArrayList<>();
        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());
        userDto.setRoles(roleDtos);
        userDto.setAccountStatus(AccountStatus.ACTIVE);

        userService.saveUser(userDto);

        //////////////////////////////////////////////////////////////////////////////////////

        userDto = new UserDto();
        userDto.setName("CZTERY");
        userDto.setPhoneNumber("444444444");
        userDto.setMainRoleType(MainRoleType.CLIENT);
        userDto.setEmail("client@pizza.pl");
        userDto.setName("NAME CZTERY");
        userDto.setLastName("NAZWISKO CZTERY");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setAddressDto(defaultAddress());

        roleDtos = new ArrayList<>();
//        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());
        userDto.setRoles(roleDtos);
        userDto.setAccountStatus(AccountStatus.ACTIVE);

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
