package pl.tu.kielce.pizza.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.department.model.jpa.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d where d.active = true ")
    List<Department> findAllActive();

    @Query("select d.pantry.id from Department d where d.id = :departmentId")
    Long fetchPantryId(@Param("departmentId") Long departmentId);
}
