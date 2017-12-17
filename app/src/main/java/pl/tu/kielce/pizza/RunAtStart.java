package pl.tu.kielce.pizza;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.tu.kielce.pizza.be.order.repository.OrderRepository;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;
import pl.tu.kielce.pizza.be.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        defaultItems();
        defaultingredients();
        defaultPizzas();

        System.out.println("ZAPISANO DANE");
    }

    private void defaultPizzas() {


        makePizza("Margherita", 22.10D, 3, 4, 5);
        makePizza("Funghi", 22.95D, 3, 4, 5, 6);
        makePizza("Salami", 23.80D, 3, 4, 5, 7);
        makePizza("Rustica", 23.80D, 3, 4, 5, 8);
        makePizza("Wegetariana", 23.80D, 3, 4, 5, 6, 9, 10);
        makePizza("Hawajska", 23.80D, 3, 4, 5, 1, 14);
        makePizza("Wiejska", 23.80D, 3, 4, 5, 11, 12, 13);
        makePizza("Capricciosa", 23.80D, 3, 4, 1, 5, 6, 9);
        makePizza("Studencka", 23.80D, 3, 4, 6, 5, 1, 18);
        makePizza("Fantazja", 23.80D, 3, 4, 1, 5, 18, 9);
        makePizza("Rafaello", 23.80D, 3, 4, 5, 6, 1, 12);
    }

    private void defaultItems() {

        addItem("Coca-Cola", 3.30D);
        addItem("Fanta", 3.30D);
        addItem("Kawa rozpuszczalna", 7D);
        addItem("Capuccino", 5.5D);
        addItem("Latte", 10D);
        addItem("Latte blue", 11D);
        addItem("Czekolada na gorąco", 5D);
        addItem("Herbata Owocowa", 5D);
        addItem("Herbata Zielona", 5D);
        addItem("Herbata Miętowa", 5D);
        addItem("Frytki", 4D);

    }

    private void addItem(String name, double price) {
        ItemDto itemDto = ItemDto
                .builder()
                .name(name)
                .price(price)
                .build();

        itemService.create(itemDto);
    }

    private List<IngredientDto> defaultingredients() {

        List<IngredientDto> ingredientDtoList = new ArrayList<>();

        IngredientDto ingredientDto = IngredientDto.builder().name("Szynka").selected(true).build();
        ingredientService.create(ingredientDto);
        ingredientDto.setId(1L);
        ingredientDtoList.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("Ser").selected(true).build();
        ingredientService.create(ingredientDto);
        ingredientDto.setId(2L);
        ingredientDtoList.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("Sos pomidorowy").build();
        ingredientService.create(ingredientDto);

        ingredientDto = IngredientDto.builder().name(" Ser mozzarella").build();
        ingredientService.create(ingredientDto);

        addIngredient("Szynka");
        addIngredient("Ser");
        addIngredient("Sos pomidorowy");
        addIngredient("Ser mozzarella");


        addIngredient("Oregano");
        addIngredient("Pieczarki");
        addIngredient("Salami");
        addIngredient("Boczek");
        addIngredient("Papryka");
        addIngredient("Kukurydza");

        addIngredient("Cebula");
        addIngredient("Kiełbasa");
        addIngredient("Ogórek kiszony");
        addIngredient("Ananas");
        addIngredient("Wołowina");

        addIngredient("Kurczak");
        addIngredient("Tymianek");
        addIngredient("Oliwki zielone");
        return ingredientDtoList;
    }


    private void makePizza(String pizzaName, Double pizzaPrice, Integer... idsOfIngredients) {
        List<IngredientDto> ingredientDtos = Arrays
                .stream(idsOfIngredients)
                .map(Integer::longValue)
                .map(aLong -> IngredientDto.builder().id(aLong).selected(true).build())
                .collect(Collectors.toList());


        PizzaDto pizzaDto = PizzaDto
                .builder()
                .name(pizzaName)
                .price(pizzaPrice)
                .ingredients(ingredientDtos)
                .build();

        pizzaService.create(pizzaDto);
    }

    private void addIngredient(String name) {
        IngredientDto ingredientDto = IngredientDto.builder().name(name).build();
        ingredientService.create(ingredientDto);
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
        userDto.setAvatarLocation("/images/default_avatar.png");

        List<RoleDto> roleDtos = new ArrayList<>();
        roleDtos.add(RoleDto.builder().id(1L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(2L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(3L).selected(true).build());
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());

        userDto.setRoles(roleDtos);
        userService.saveUser(userDto);
        userDto = new UserDto();
        userDto.setMainRoleType(MainRoleType.MANAGER);
        userDto.setAvatarLocation("/images/default_avatar.png");

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

        userDto = new UserDto();
        userDto.setMainRoleType(MainRoleType.USER);
        userDto.setPhoneNumber("333333333");
        userDto.setName("TRZY");
        userDto.setAvatarLocation("/images/default_avatar.png");
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


        userDto = new UserDto();
        userDto.setName("CZTERY");
        userDto.setPhoneNumber("444444444");
        userDto.setMainRoleType(MainRoleType.CLIENT);
        userDto.setEmail("client@pizza.pl");
        userDto.setName("NAME CZTERY");
        userDto.setAvatarLocation("/images/default_avatar.png");
        userDto.setLastName("NAZWISKO CZTERY");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setAddressDto(defaultAddress());

        roleDtos = new ArrayList<>();
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
