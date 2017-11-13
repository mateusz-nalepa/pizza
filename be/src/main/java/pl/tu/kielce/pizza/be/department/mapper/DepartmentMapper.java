package pl.tu.kielce.pizza.be.department.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.be.common.mapper.CommonMapper;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    @Autowired
    private final CommonMapper commonMapper;

    public DepartmentDto entityToDto(Department entity) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(entity.getId());
        departmentDto.setAddressDto(commonMapper.addressEntityToDto(entity.getAddress()));
        departmentDto.setMultiplier(entity.getMultiplier());
        commonMapper.baseEntityToDto(entity, departmentDto);
        return departmentDto;
    }

    public Department dtoToEntity(DepartmentDto dto) {

        Department department = new Department();
        department.setAddress(commonMapper.addressDtoToEntity(dto.getAddressDto()));
//        department.setEmployees(new ArrayList<User>());
//        department.setIngredientDepartments(new ArrayList<Ingredient>());
//        department.setPizzas(new ArrayList<Pizza>());
        department.setMultiplier(dto.getMultiplier());
//        department.setManager(userMapper.dtoToEntity(dto.getManager()));
        department.activate();

        return department;
    }
}
