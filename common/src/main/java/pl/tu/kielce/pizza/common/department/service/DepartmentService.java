package pl.tu.kielce.pizza.common.department.service;


import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto create(DepartmentDto departmentDto);

    List<DepartmentDto> findAll();

    DepartmentDto findOne(Long departmentId);
}
