package pl.tu.kielce.pizza.common.security.service;

import pl.tu.kielce.pizza.common.security.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();
}
