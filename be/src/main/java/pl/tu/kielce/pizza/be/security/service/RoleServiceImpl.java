package pl.tu.kielce.pizza.be.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.be.security.repository.role.RoleExecutor;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.service.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleExecutor roleExecutor;

    @Override
    public List<RoleDto> findAll() {
        return roleExecutor.findAll();
    }
}
