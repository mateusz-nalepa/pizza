package pl.tu.kielce.pizza;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;
import pl.tu.kielce.pizza.be.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class RunAtStart {

    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserService userService;

    @PostConstruct
    public void runAtStart() {
        saveDefaultRoles();
        defaultUsers();
        defaultDepartment();
        findAllDepartment();
        System.out.println("addDefaultDepartment");
    }

    private void saveDefaultRoles() {
        Role role = Role.builder().role("MANAGER").build();
        roleRepository.save(role);

        role = Role.builder().role("USER").build();
        roleRepository.save(role);

        role = Role.builder().role("ADMIN").build();
        roleRepository.save(role);
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
        departmentDto.setMultiplier(10.0D);
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
        userDto.setRoles(defaultRoles());
        userService.saveUser(userDto);

        userDto = new UserDto();
        userDto.setName("DWA");
        userDto.setEmail("dwa@pizza.pl");
        userDto.setName("NAME DWA");
        userDto.setLastName("NAZWISKO DWA");
        userDto.setPassword("asd123");
        userDto.setActive(true);
        userDto.setRoles(defaultRoles());
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
