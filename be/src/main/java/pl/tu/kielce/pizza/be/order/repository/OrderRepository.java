package pl.tu.kielce.pizza.be.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.be.order.model.jpa.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
