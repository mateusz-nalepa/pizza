package pl.tu.kielce.pizza.be.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.be.item.model.jpa.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
