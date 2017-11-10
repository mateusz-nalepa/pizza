package pl.tu.kielce.pizza.be.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PIZZA_STATUS")
public class PizzaSize {


    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private PizzaType pizzaType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaSize")
    private List<Pizza> pizzas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizzaSize")
    private List<BoughtPizza> boughtPizzas;
}
