package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.be.order.model.jpa.enums.pizzatype.PizzaType;
import pl.tu.kielce.pizza.be.pizza.model.jpa.Pizza;

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

//    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToOne
    private Order order;
//    private String name;
//    private String description;
    private Double purchasePrice;


//    @Enumerated(EnumType.ORDINAL)
//    private PizzaType pizzaType;

    private Double quantity;

    @Enumerated(EnumType.ORDINAL)
    private PizzaType pizzaType;

    @ManyToOne
    private Department department;

    @OneToOne
    private Pizza pizza;
}
