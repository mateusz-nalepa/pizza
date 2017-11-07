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
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Ingredient ingredient;

    private Double quantity;
}
