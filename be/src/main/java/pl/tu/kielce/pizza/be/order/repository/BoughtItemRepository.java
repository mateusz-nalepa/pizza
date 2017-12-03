package pl.tu.kielce.pizza.be.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.be.order.model.jpa.BoughtItem;

public interface BoughtItemRepository extends JpaRepository<BoughtItem, Long> {
}
