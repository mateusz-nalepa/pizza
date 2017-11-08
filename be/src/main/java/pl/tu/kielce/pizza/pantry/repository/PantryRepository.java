package pl.tu.kielce.pizza.pantry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.pantry.model.jpa.Pantry;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    @Query("select d.pantry from Department d where d.id = :departmentId")
    Pantry findByDepartmendId(@Param("departmentId") Long departmentId);
}
