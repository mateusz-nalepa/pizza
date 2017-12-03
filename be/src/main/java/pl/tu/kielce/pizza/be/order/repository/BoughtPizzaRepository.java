package pl.tu.kielce.pizza.be.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.be.order.model.jpa.BoughtPizza;

public interface BoughtPizzaRepository extends JpaRepository<BoughtPizza, Long> {
}
