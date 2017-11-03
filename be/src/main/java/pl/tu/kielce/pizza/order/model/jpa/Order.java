package pl.tu.kielce.pizza.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.department.model.jpa.Department;
import pl.tu.kielce.pizza.order.enums.OrderStatus;
import pl.tu.kielce.pizza.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.security.model.jpa.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Pizza pizza;

    @OneToOne
    private User user;

    @OneToOne
    private Department department;

    private Double multiplier;

    private LocalDateTime dateOfOrder;

    private LocalDateTime dateOfRelease;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

}
