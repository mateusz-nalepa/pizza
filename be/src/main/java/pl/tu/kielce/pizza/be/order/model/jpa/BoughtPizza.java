package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.common.common.enums.PizzaType;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BoughtPizza {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;
    private Double purchasePrice;

    private Double quantity;

    @Enumerated(EnumType.ORDINAL)
    private PizzaType pizzaType;

    @ManyToOne
    private Department department;

    @OneToOne
    private Pizza pizza;
}
