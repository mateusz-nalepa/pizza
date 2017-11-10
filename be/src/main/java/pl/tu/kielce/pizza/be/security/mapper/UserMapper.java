package pl.tu.kielce.pizza.be.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.common.mapper.CommonMapper;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;
import pl.tu.kielce.pizza.be.security.model.jpa.User;
import pl.tu.kielce.pizza.be.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final CommonMapper commonMapper;

    public User dtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setRoles(extractRoles(dto));
        entity.activate();
        return entity;
    }

    private Set<Role> extractRoles(UserDto dto) {
        return dto
                .getRoles()
                .stream()
                .map(RoleDto::getRole)
                .map(this::createRole)
                .collect(Collectors.toSet());
    }

    private Role createRole(String role) {
        return roleRepository.findByRole(role);
    }

    public UserDto entityToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(extractRoles(user));

        return commonMapper.baseEntityToDto(user, userDto);
    }

    private Set<RoleDto> extractRoles(User user) {
        return user
                .getRoles()
                .stream()
                .map(Role::getRole)
                .map(this::createRoleDto)
                .collect(Collectors.toSet());
    }

    private RoleDto createRoleDto(String role) {
        return
                RoleDto
                        .builder()
                        .role(role)
                        .build();
    }
}
