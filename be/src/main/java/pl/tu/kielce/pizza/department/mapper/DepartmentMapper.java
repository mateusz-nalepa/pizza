package pl.tu.kielce.pizza.department.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.common.mapper.CommonMapper;
import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.model.jpa.Department;
import pl.tu.kielce.pizza.security.mapper.UserMapper;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {


    @Autowired
    private final CommonMapper commonMapper;

    @Autowired
    private final UserMapper userMapper;

    public DepartmentDto entityToDto(Department entity) {

        return DepartmentDto
                .builder()
                .multiplier(entity.getMultiplier())
                .id(entity.getId())
                .active(entity.isActive())
                .manager(userMapper.entityToDto(entity.getManager()))
                .address(commonMapper.addressEntityToDto(entity.getAddress()))
                .build();

    }

    public Department dtoToEntity(DepartmentDto dto) {

        return Department
                .builder()
                .active(dto.isActive())
                .multiplier(dto.getMultiplier())
                .address(commonMapper.addressDtoToEntity(dto.getAddress()))
                .build();
    }
}
