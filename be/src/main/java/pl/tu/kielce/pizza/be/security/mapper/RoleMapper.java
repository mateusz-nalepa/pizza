package pl.tu.kielce.pizza.be.security.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;
import pl.tu.kielce.pizza.be.security.model.jpa.User;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public RoleDto entityToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRole(role.getRole());

        return roleDto;
    }

    public Role entityToDto(RoleDto roleDto) {

        Role role = new Role();
        roleDto.setRole(role.getRole());

        return role;
    }

    List<RoleDto> extractRoles(User user) {
        return user
                .getRoles()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

}
