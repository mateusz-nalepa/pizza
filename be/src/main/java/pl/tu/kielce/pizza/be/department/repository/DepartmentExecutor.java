package pl.tu.kielce.pizza.be.department.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.be.department.mapper.DepartmentMapper;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.be.security.model.jpa.User;
import pl.tu.kielce.pizza.be.security.repository.user.UserRepository;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.dto.FreeManagerDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static pl.tu.kielce.pizza.be.common.util.BeFunctionsUtil.doRead;

@Repository
@RequiredArgsConstructor
public class DepartmentExecutor {


    @PersistenceContext
    private EntityManager em;

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final DepartmentMapper departmentMapper;

    @Autowired
    private final UserRepository userRepository;


//    @Transactional
//    public DepartmentDto getById(Long departmentId) {
//        Department entity = departmentRepository.findOne(departmentId);
//        DepartmentDto departmentDto = departmentMapper.entityToDto(entity);
//        departmentDto.setPantry(fetchPantry(departmentId));
//        return departmentDto;
//    }

    public List<DepartmentDto> findAll() {

        return departmentRepository
                .findAll()
                .stream()
                .map(departmentMapper::entityToDto)
                .collect(Collectors.toList());
    }
//
//    public List<FreeManagerDto> freeManagers() {
//
//        List<Object[]> freeManagers = userRepository.findFreeManagers("MANAGER");
//        return NativeResultQuerySetHandler.resultList(freeManagers, FreeManagerDto.class);
//
//    }

//    private PantryDto fetchPantry(Long departmentId) {
//        Pantry pantryEntity = pantryRepository.findByDepartmendId(departmentId);
//        return pantryMapper.entityToDto(pantryEntity);
//    }


    public DepartmentDto create(DepartmentDto departmentDto) {
        Department department = departmentMapper.dtoToEntity(departmentDto);

        User user = userRepository.findOne(departmentDto.getManager().getId());
        user.setDepartment(department);
        user = userRepository.save(user);

        return departmentMapper.entityToDto(user.getDepartment());
    }

    public List<FreeManagerDto> freeManagers() {
        userRepository.findFreeManagers();

        return null;
    }

    public DepartmentDto findOne(Long departmentId) {
        return doRead(
                departmentId,
                departmentRepository::findOne,
                departmentMapper::entityToDto
        );
    }


    public Double multiplier(Long userId) {

        return departmentRepository
                .multiplierByUserId(userId);
    }
}
