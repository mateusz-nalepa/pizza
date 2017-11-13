package pl.tu.kielce.pizza.be.security.repository.role;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.security.mapper.RoleMapper;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RoleExecutor {

    @Autowired
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public Set<RoleDto> findAll() {
        return roleRepository
                .findAll()
                .stream()
                .map(roleMapper::entityToDto)
                .collect(Collectors.toSet());
    }
}
