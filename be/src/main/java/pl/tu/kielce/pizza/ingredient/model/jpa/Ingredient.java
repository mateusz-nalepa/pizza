package pl.tu.kielce.pizza.ingredient.model.jpa;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Item item;

    private Double quantity;

    private Long pantryId;
}
