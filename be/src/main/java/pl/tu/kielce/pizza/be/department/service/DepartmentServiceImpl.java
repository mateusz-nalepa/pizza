package pl.tu.kielce.pizza.be.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.department.repository.DepartmentExecutor;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private final DepartmentExecutor departmentExecutor;

    @Override
    @Transactional
    public DepartmentDto create(DepartmentDto departmentDto) {
        return departmentExecutor.create(departmentDto);
    }

    @Override
    public List<DepartmentDto> findAll() {
        return departmentExecutor.findAll();
    }

    @Override
    public DepartmentDto findOne(Long departmentId) {
        return departmentExecutor.findOne(departmentId);
    }

    @Override
    public Double multiplier(Long userId) {
        return departmentExecutor.multiplier(userId);
    }

//    @Override
//    public DepartmentDto getById(Long departmentId) {
//        return departmentExecutor.getById(departmentId);
//    }
//
//    @Override
//    public List<DepartmentDto> findAll() {
//        return departmentExecutor.findAll();
//    }
//
//    @Override
//    public DepartmentDto create(DepartmentDto departmentDto) {
//        return departmentExecutor.save(departmentDto);
//    }
//
//    @Override
//    public List<FreeManagerDto> freeManagers() {
//        return departmentExecutor.freeManagers();
//    }
}
