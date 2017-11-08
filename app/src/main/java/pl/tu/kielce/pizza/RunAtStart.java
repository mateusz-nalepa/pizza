package pl.tu.kielce.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.common.dto.AddressDto;
import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.repository.DepartmentExecutor;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.dto.ItemDto;
import pl.tu.kielce.pizza.ingredient.executor.IngredientExecutor;
import pl.tu.kielce.pizza.ingredient.executor.ItemExecutor;
import pl.tu.kielce.pizza.ingredient.service.IngredientService;
import pl.tu.kielce.pizza.security.dto.RoleDto;
import pl.tu.kielce.pizza.security.dto.UserDto;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.security.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class RunAtStart
//        implements CommandLineRunner
{

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final DepartmentExecutor departmentExecutor;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final ItemExecutor itemExecutor;

    @Autowired
    private final IngredientExecutor ingredientExecutor;

    @Autowired
    private final IngredientService ingredientService;

//
    @PostConstruct
    public void run()  {
        createRoles();
        defaultUsers();
        List<ItemDto> itemDtos = defaultItems();
        defaultDepartment(itemDtos);
        departmentExecutor.freeManagers();
        addDefaultIngredientsToPantry();
        fetchDepartment();
    }

    private void fetchDepartment() {
        departmentExecutor.getById(1L);
    }

    private void addDefaultIngredientsToPantry() {
        IngredientDto ingredientDto = IngredientDto
                .builder()
                .itemDto(ItemDto.builder().id(1L).build())
                .pantryId(1L)
                .quantity(20.0)
                .pantryId(1L)
                .build();
        ingredientExecutor.add(ingredientDto);

//        ingredientDto = IngredientDto
//                .builder()
//                .itemDto(ItemDto.builder().id(2L).build())
//                .pantryId(1L)
//                .quantity(10.0)
//                .build();
//        ingredientExecutor.add(ingredientDto);
    }

    private List<ItemDto> defaultItems() {

        List<ItemDto> items = new ArrayList<>();
        ItemDto itemDto = ItemDto.builder().name("Mąka").description("Używana do pieczenia").build();
        ItemDto add = itemExecutor.add(itemDto);

        itemDto = ItemDto.builder().name("Ser").description("Pizza się dzięki niemu ciągnie").build();
        ItemDto add1 = itemExecutor.add(itemDto);

        itemDto = ItemDto.builder().name("Szynka").description("Nadaje unikalego smaku").build();
        ItemDto add2 = itemExecutor.add(itemDto);

        items.add(add);
        items.add(add1);
        items.add(add2);
        return items;
    }

    private void createRoles() {
        List<Role> roles = Arrays.asList(
                Role.builder().role("ADMIN").build(),
                Role.builder().role("MANAGER").build(),
                Role.builder().role("USER").build()
        );
        roleRepository.save(roles);
    }

    private void defaultUsers() {
        UserDto userDto = UserDto
                .builder()
                .name("HODOREK")
                .email("admin@pizza.pl")
                .name("Mateusz")
                .lastName("Nalepa")
                .password("asd123")
                .active(true)
                .roles(defaultRoles())
                .build();

        userService.saveUser(userDto);

        userDto = UserDto
                .builder()
                .name("HODOREK2")
                .email("admin2@pizza.pl")
                .name("Mateusz2")
                .lastName("Nalepa2")
                .password("asd123")
                .active(true)
                .roles(defaultRoles())
                .build();

        userService.saveUser(userDto);
    }

    private Set<RoleDto> defaultRoles() {
        RoleDto adminRole = RoleDto.builder().role("ADMIN").build();
        RoleDto managerRole = RoleDto.builder().role("MANAGER").build();
        RoleDto userRole = RoleDto.builder().role("USER").build();
        Set<RoleDto> roleDtos = new HashSet<>();
        roleDtos.add(adminRole);
        roleDtos.add(userRole);
        roleDtos.add(managerRole);
        return roleDtos;

    }

    private void defaultDepartment(List<ItemDto> itemDtos) {

        DepartmentDto departmentDto = DepartmentDto
                .builder()
                .multiplier(0.10)
                .manager(UserDto.builder().id(1L).build())
                .active(true)
                .address(defaultAddress())
                .build();

        departmentDto = departmentExecutor.save(departmentDto);
        System.out.println("--------------I'm saved department--------------");
    }

    private AddressDto defaultAddress() {
        return AddressDto
                .builder()
                .city("Kielce")
                .street("Wiosenna")
                .houseNumber(5)
                .flatNumber(28)
                .build();
    }


    @PostConstruct
    public void  asd() {
//        List<FreeItemDto> freeItemDtos = ingredientService.itemsNotAssignedToDepartment(1L);
//        ItemDto itemDto = ItemDto.builder().id(2L).build();
//        ingredientService.addIngredientToDepartment(itemDto,1L);
    }
}
