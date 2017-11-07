package pl.tu.kielce.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.common.dto.AddressDto;
import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.repository.DepartmentExecutor;
import pl.tu.kielce.pizza.ingredient.dto.IngredientDto;
import pl.tu.kielce.pizza.ingredient.executor.IngredientExecutor;
import pl.tu.kielce.pizza.security.dto.RoleDto;
import pl.tu.kielce.pizza.security.dto.UserDto;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.security.service.UserServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RunAtStart implements CommandLineRunner {

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final DepartmentExecutor departmentExecutor;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final IngredientExecutor ingredientExecutor;

    @Override
    public void run(String... strings) throws Exception {
        createRoles();
        defaultUsers();
        defaultDepartment();
        defaultIngredients();
        departmentExecutor.freeManagers();
    }

    private void defaultIngredients() {
        IngredientDto ingredientDto = IngredientDto.builder().name("Mąka").description("Używana do pieczenia").build();
        ingredientExecutor.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("Ser").description("Pizza się dzięki niemu ciągnie").build();
        ingredientExecutor.add(ingredientDto);

        ingredientDto = IngredientDto.builder().name("Szynka").description("Nadaje unikalego smaku").build();
        ingredientExecutor.add(ingredientDto);
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

    private void defaultDepartment() {

        DepartmentDto departmentDto = DepartmentDto
                .builder()
                .multiplier(0.10)
                .manager(UserDto.builder().id(1L).build())
                .active(true)
                .address(defaultAddress())
                .build();

        departmentDto = departmentExecutor.save(departmentDto);
        System.out.println("ZAPISALEM SIE");
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

}
