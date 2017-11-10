package pl.tu.kielce.pizza.be.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d where d.active = true")
    List<Department> findAll();

}
