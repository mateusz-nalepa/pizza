package pl.tu.kielce.pizza.common.security.service;

import pl.tu.kielce.pizza.common.security.dto.RoleDto;

import java.util.Set;

public interface RoleService {

    Set<RoleDto> findAll();
}
