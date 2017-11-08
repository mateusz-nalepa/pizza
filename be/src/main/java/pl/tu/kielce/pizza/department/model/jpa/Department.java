package pl.tu.kielce.pizza.department.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.model.jpa.Address;
import pl.tu.kielce.pizza.pantry.model.jpa.Pantry;
import pl.tu.kielce.pizza.pizza.model.jpa.Pizza;
import pl.tu.kielce.pizza.security.model.jpa.User;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEPARTMENT")
public class Department {


    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private Pantry pantry;

    @OneToOne
    private User manager;

    @OneToMany
    private List<User> employees;

    @OneToMany
    private List<Pizza> pizzas;

    private Double multiplier;

    private boolean active;

    public void activate() {
        this.active = true;
    }

    public void deActivate() {
        this.active = false;
    }
}
