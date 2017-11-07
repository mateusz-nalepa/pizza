package pl.tu.kielce.pizza.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.tu.kielce.pizza.department.model.jpa.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d where d.active = true ")
    List<Department> findAllActive();
}
