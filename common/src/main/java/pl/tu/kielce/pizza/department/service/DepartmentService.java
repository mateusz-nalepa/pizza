package pl.tu.kielce.pizza.department.service;

import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.dto.FreeManagerDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto getById(Long departmentId);

    List<DepartmentDto> findAll();

    DepartmentDto add(DepartmentDto departmentDto);

    List<FreeManagerDto> freeManagers();
}
