package pl.tu.kielce.pizza.be.pizza.model.jpa;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class Ingredient {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private boolean active;
    
    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Pizza> pizzas;
    
}
