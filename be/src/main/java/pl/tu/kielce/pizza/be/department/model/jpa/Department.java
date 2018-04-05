package pl.tu.kielce.pizza.be.department.model.jpa;

import lombok.*;
import pl.tu.kielce.pizza.be.common.jpa.Address;
import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.be.security.model.jpa.User;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Department extends AuditableEntity{


    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @Builder.Default
    private List<User> employees = new ArrayList<>();

    @OneToOne(mappedBy = "department")
    @JoinColumn(name = "manager_id")
    private User manager;

    private String phoneNumber;
    private LocalTime openHour;
    private LocalTime closeHour;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
//    private List<Ingredient> ingredientDepartments;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
//    private List<Pizza> boughtPizzas;

    private Double multiplier;

}
