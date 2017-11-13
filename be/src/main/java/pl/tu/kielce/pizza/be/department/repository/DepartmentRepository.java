package pl.tu.kielce.pizza.be.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d where d.active = true")
    List<Department> findAll();

    @Query("select u.department.multiplier from User u where u.id = :userId")
    Double multiplierByUserId(@Param("userId") Long userId);

}
