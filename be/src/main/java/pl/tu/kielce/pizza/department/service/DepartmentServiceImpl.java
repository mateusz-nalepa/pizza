package pl.tu.kielce.pizza.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tu.kielce.pizza.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.department.repository.DepartmentExecutor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private final DepartmentExecutor departmentExecutor;

    @Override
    public DepartmentDto getById(Long departmentId) {
        return departmentExecutor.getById(departmentId);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentExecutor.findAll();
    }

    @Override
    public DepartmentDto add(DepartmentDto departmentDto) {
        return departmentExecutor.save(departmentDto);
    }

    @Override
    public List<FreeManagerDto> freeManagers() {
        return departmentExecutor.freeManagers();
    }
}
