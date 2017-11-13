package pl.tu.kielce.pizza.be.pizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    @Query("select p from Pizza p where  p.active = true")
    List<Pizza> findAllActive();
}
