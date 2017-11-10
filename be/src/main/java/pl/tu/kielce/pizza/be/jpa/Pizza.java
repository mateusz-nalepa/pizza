package pl.tu.kielce.pizza.be.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PIZZA")
public class Pizza {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pizza")
    private List<IngredientPizza> ingredientDepartments;

    private String name;
    private String description;
    private Double price;

    @ManyToOne
    private PizzaSize pizzaSize;

    @ManyToOne
    private Department department;
}
