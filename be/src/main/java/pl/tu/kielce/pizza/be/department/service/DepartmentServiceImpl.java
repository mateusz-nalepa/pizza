package pl.tu.kielce.pizza.be.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tu.kielce.pizza.be.department.repository.DepartmentExecutor;
import pl.tu.kielce.pizza.common.department.dto.ChooseDepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.FreeUserDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private final DepartmentExecutor departmentExecutor;

    @Autowired
    private final UserContext userContext;

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

    @Override
    public void setDepartmentInContext(ChooseDepartmentDto chooseDepartmentDto) {
        DepartmentDto departmentDto = departmentExecutor.findOne(chooseDepartmentDto.getDepartmentId());
        userContext.setDepartmentDto(departmentDto);
        userContext.setMultiplier(departmentDto.getMultiplier());
        userContext.setUserOrderDto(new UserOrderDto());
    }

    @Override
    @Transactional
    public void addEmployeesToDepartmentPost(List<FreeUserDto> freeUsers) {

        departmentExecutor.addEmployeesToDepartmentPost(freeUsers);

    }

    @Override
    public List<UserDto> findWorkersByDepartmentId(Long departmentId) {
        return departmentExecutor.findWorkersByDepartmentId(departmentId);
    }

    @Override
    public DepartmentDto departmentByUser(Long userId) {
        return departmentExecutor.departmentByUser(userId);
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
