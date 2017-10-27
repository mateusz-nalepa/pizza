package pl.tu.kielce.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.security.dto.RoleDto;
import pl.tu.kielce.pizza.security.dto.UserDto;
import pl.tu.kielce.pizza.security.mapper.UserMapper;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.security.service.UserServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
//@RequiredArgsConstructor
public class RunAtStart implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(String... strings) throws Exception {
        createRoles();
        defaultUser();
    }

    private void createRoles() {
        List<Role> roles = Arrays.asList(
                Role.builder().role("ADMIN").build(),
                Role.builder().role("MANAGER").build(),
                Role.builder().role("USER").build()
        );
        roleRepository.save(roles);
    }

    private void defaultUser() {
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

}
