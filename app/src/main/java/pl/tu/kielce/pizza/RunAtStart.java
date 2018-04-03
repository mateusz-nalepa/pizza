package pl.tu.kielce.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pl.tu.kielce.pizza.aspect.TrackTime;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class RunAtStart
{
    
    @Autowired
    private Environment environment;
    
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
    
    //    @PostConstruct
    @TrackTime
    public void runAtStart()
    {
    
        System.out.println(environment.getProperty("someProperty"));
    
        defaultRoles();
        defaultUsers();
        defaultDepartment();
        defaultItems();
        defaultingredients();
        defaultPizzas();
        
        System.out.println("ZAPISANO DANE");
    }
    
    private void defaultPizzas()
    {

//        makePizza("Margherita", 22.10D, 3, 4, 5);
//        makePizza("Funghi", 22.95D, 3, 4, 5, 6);
        makePizza("Salami", 23.80D, 3, 4, 5, 7);
//        makePizza("Rustica", 24.20D, 3, 4, 5, 8);
        makePizza("Wegetariana", 24.50D, 3, 4, 5, 6, 9, 10);
//        makePizza("Hawajska", 25.40D, 3, 4, 5, 1, 14);
        makePizza("Wiejska", 25.90D, 3, 4, 5, 11, 12, 13);
//        makePizza("Capricciosa", 26.30D, 3, 4, 1, 5, 6, 9);
//        makePizza("Studencka", 26.80D, 3, 4, 6, 5, 1, 18);
//        makePizza("Fantazja", 27.40D, 3, 4, 1, 5, 18, 9);
//        makePizza("Rafaello", 28.80D, 3, 4, 5, 6, 1, 12);
    }
    
    private void defaultItems()
    {
        
        addItem("Coca-Cola", 3.30D);
//        addItem("Fanta", 3.30D);
//        addItem("Kawa rozpuszczalna", 7D);
//        addItem("Capuccino", 5.5D);
//        addItem("Latte", 10D);
//        addItem("Latte blue", 11D);
//        addItem("Czekolada na gorąco", 5D);
//        addItem("Herbata Owocowa", 5D);
//        addItem("Herbata Zielona", 5D);
//        addItem("Herbata Miętowa", 5D);
//        addItem("Frytki", 4D);
    
    }
    
    public void addItem(String name, double price)
    {
        ItemDto itemDto = ItemDto
            .builder()
            .name(name)
            .price(price)
            .build();
        
        itemService.create(itemDto);
//        throw new RuntimeException("asdasdasdasd");
    }
    
    private List<IngredientDto> defaultingredients()
    {
        
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
        ingredientService.create(ingredientDto);
        
        ingredientDto = IngredientDto.builder().name(" Ser mozzarella").build();
        ingredientService.create(ingredientDto);

//        addIngredient("Szynka");
//        addIngredient("Ser");
//        addIngredient("Sos pomidorowy");
//        addIngredient("Ser mozzarella");

//        addIngredient("Oregano");
//        addIngredient("Pieczarki");
//        addIngredient("Salami");
//        addIngredient("Boczek");
//        addIngredient("Papryka");
//        addIngredient("Kukurydza");
//
//        addIngredient("Cebula");
//        addIngredient("Kiełbasa");
//        addIngredient("Ogórek kiszony");
//        addIngredient("Ananas");
//        addIngredient("Wołowina");
//
//        addIngredient("Kurczak");
//        addIngredient("Tymianek");
//        addIngredient("Oliwki zielone");
        return ingredientDtoList;
    }
    
    private void makePizza(String pizzaName, Double pizzaPrice, Integer... idsOfIngredients)
    {
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
    
    private void addIngredient(String name)
    {
        IngredientDto ingredientDto = IngredientDto.builder().name(name).build();
        ingredientService.create(ingredientDto);
    }
    
    private void defaultDepartment()
    {
        UserDto manager = new UserDto();
        manager.setId(1L);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setAddressDto(defaultAddress());
        departmentDto.setManager(manager);
        departmentDto.setMultiplier(0.10D);
        departmentDto.setActive(true);
        departmentDto.setPhoneNumber("111111111");
        departmentDto.setOpenHour(LocalTime.of(15, 0));
        departmentDto.setCloseHour(LocalTime.of(23, 0));
        departmentService.create(departmentDto);

//        UserDto manager = new UserDto();
//        manager.setId(1L);
        departmentDto = new DepartmentDto();
        departmentDto.setAddressDto(defaultAddress("Warszawa", "Kielecka", 5, 4));
        departmentDto.setManager(manager);
        departmentDto.setMultiplier(0.20D);
        departmentDto.setActive(true);
        departmentDto.setPhoneNumber("712394726");
        departmentDto.setOpenHour(LocalTime.of(15, 0));
        departmentDto.setCloseHour(LocalTime.of(23, 0));
        departmentService.create(departmentDto);
        
        departmentDto = new DepartmentDto();
        departmentDto.setAddressDto(defaultAddress("Radom", "Sosnowska", 7, 23));
        departmentDto.setManager(manager);
        departmentDto.setMultiplier(0.15D);
        departmentDto.setActive(true);
        departmentDto.setPhoneNumber("712394727");
        departmentDto.setOpenHour(LocalTime.of(13, 0));
        departmentDto.setCloseHour(LocalTime.of(23, 0));
        departmentService.create(departmentDto);

//        manager = new UserDto();
//        manager.setId(2L);
//        departmentDto = new DepartmentDto();
//        departmentDto.setAddressDto(defaultAddress());
//        departmentDto.setManager(manager);
//        departmentDto.setMultiplier(0.15D);
//        departmentDto.setActive(true);
//        departmentDto.setPhoneNumber("111111189");
//        departmentDto.setOpenHour(LocalTime.of(15, 0));
//        departmentDto.setCloseHour(LocalTime.of(23, 0));
//        departmentService.create(departmentDto);

//        manager = new UserDto();
//        manager.setId(2L);
//        departmentDto = new DepartmentDto();
//        departmentDto.setAddressDto(defaultAddress());
////        departmentDto.setManager(manager);
//        departmentDto.setMultiplier(0.20D);
//        departmentDto.setPhoneNumber("111111111");
//        departmentDto.setActive(true);
//        departmentService.create(departmentDto);
        
        UserDto byEmail = userService.findByEmail("manager@pizza.pl");
        
    }
    
    private AddressDto defaultAddress(String city, String street, int houseNumber, int flatNumber)
    {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(city);
        number++;
        addressDto.setStreet(street);
        addressDto.setHouseNumber(houseNumber);
        addressDto.setFlatNumber(flatNumber);
        return addressDto;
    }
    
    private AddressDto defaultAddress()
    {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Kielce " + number);
        number++;
        addressDto.setStreet("Tysiąclecia P.P");
        addressDto.setHouseNumber(1);
        addressDto.setFlatNumber(1);
        return addressDto;
    }
    
    private void defaultUsers()
    {
        UserDto userDto = new UserDto();
        userDto.setName("HODOREK");
        userDto.setEmail("admin@pizza.pl");
        userDto.setName("Jan");
        userDto.setLastName("Kowalski");
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
        userDto.setName("Mariusz");
        userDto.setLastName("Nowak");
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
        userDto.setMainRoleType(MainRoleType.MANAGER);
        userDto.setPhoneNumber("333333333");
        userDto.setName("TRZY");
        userDto.setAvatarLocation("/images/default_avatar.png");
        userDto.setEmail("user1@pizza.pl");
        userDto.setName("Mariusz");
        userDto.setLastName("Nowakowski");
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
        userDto.setName("CZTERY");
        userDto.setPhoneNumber("444444444");
        userDto.setMainRoleType(MainRoleType.CLIENT);
        userDto.setEmail("client@pizza.pl");
        userDto.setName("Zenon");
        userDto.setAvatarLocation("/images/default_avatar.png");
        userDto.setLastName("Pawełkiewicz");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setAddressDto(defaultAddress());
        
        roleDtos = new ArrayList<>();
        roleDtos.add(RoleDto.builder().id(4L).selected(true).build());
        userDto.setRoles(roleDtos);
        userDto.setAccountStatus(AccountStatus.ACTIVE);
        
        userService.saveUser(userDto);
    }
    
    @TrackTime
    public void defaultRoles()
    {
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
