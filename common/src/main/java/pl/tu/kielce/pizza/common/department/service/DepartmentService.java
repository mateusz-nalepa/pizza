package pl.tu.kielce.pizza.common.department.service;


import pl.tu.kielce.pizza.common.department.dto.ChooseDepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.FreeUserDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto create(DepartmentDto departmentDto);

    List<DepartmentDto> findAll();

    DepartmentDto findOne(Long departmentId);

    Double multiplier(Long userId);

    void setDepartmentInContext(ChooseDepartmentDto chooseDepartmentDto);

    void addEmployeesToDepartmentPost(List<FreeUserDto> freeUsers);

    List<UserDto> findWorkersByDepartmentId(Long departmentId);

    DepartmentDto departmentByUser(Long userId);
}


