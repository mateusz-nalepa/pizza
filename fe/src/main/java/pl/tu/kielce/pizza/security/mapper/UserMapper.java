package pl.tu.kielce.pizza.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.dto.user.UserDto;
import pl.tu.kielce.pizza.dto.user.RoleDto;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.model.jpa.User;
import pl.tu.kielce.pizza.security.repository.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public User dtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setRoles(extractRoles(dto));
        entity.setActive(dto.isActive());
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
        return UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .lastName(user.getLastName())
                .active(user.isActive())
                .roles(extractRoles(user))
                .build();
    }

    private Set<RoleDto> extractRoles(User dto) {
        return dto
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
