package pl.tu.kielce.pizza.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.department.model.jpa.Department;
import pl.tu.kielce.pizza.ingredient.model.jpa.PizzaSize;
import pl.tu.kielce.pizza.pizza.model.jpa.PizzaType;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOUGHT_PIZZA")
public class BoughtPizza {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boughtPizza")
    private List<BoughtIngredientPizza> ingredientDepartments;

    @ManyToOne
    private Order order;
    private String name;
    private String description;
    private Double price;
    @Enumerated(EnumType.ORDINAL)
    private PizzaType pizzaType;

    @ManyToOne
    private PizzaSize pizzaSize;

    @ManyToOne
    private Department department;
}
