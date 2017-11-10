package pl.tu.kielce.pizza.be.department.model.jpa;

import lombok.*;
import pl.tu.kielce.pizza.be.common.jpa.Address;
import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.be.jpa.IngredientDepartment;
import pl.tu.kielce.pizza.be.jpa.Pizza;
import pl.tu.kielce.pizza.be.security.model.jpa.User;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEPARTMENT")
@EqualsAndHashCode(callSuper = true)
public class Department extends AuditableEntity{


    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<User> employees;

    @OneToOne(mappedBy = "department")
    @JoinColumn(name = "manager_id")
    private User manager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<IngredientDepartment> ingredientDepartments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Pizza> pizzas;

    private Double multiplier;

}
