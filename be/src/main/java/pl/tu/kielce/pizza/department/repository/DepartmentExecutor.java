package pl.tu.kielce.pizza.department.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.common.queryHandler.NativeResultQuerySetHandler;
import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.department.mapper.DepartmentMapper;
import pl.tu.kielce.pizza.department.model.jpa.Department;
import pl.tu.kielce.pizza.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.security.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DepartmentExecutor {

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final DepartmentMapper departmentMapper;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public DepartmentDto getById(Long departmentId) {
        Department entity = departmentRepository.findOne(departmentId);
        return departmentMapper.entityToDto(entity);
    }

    public DepartmentDto save(DepartmentDto departmentDto) {
        Department entity = departmentMapper.dtoToEntity(departmentDto);
        entity.setManager(userRepository.findOne(departmentDto.getManager().getId()));
        entity = departmentRepository.save(entity);
        return departmentMapper.entityToDto(entity);
    }

    public List<DepartmentDto> findAll() {

        return departmentRepository
                .findAllActive()
                .stream()
                .map(departmentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<FreeManagerDto> freeManagers() {

        List<Object[]> freeManagers = userRepository.findAllFreeManagers("MANAGER");
        return NativeResultQuerySetHandler.resultList(freeManagers, FreeManagerDto.class);

    }

}
