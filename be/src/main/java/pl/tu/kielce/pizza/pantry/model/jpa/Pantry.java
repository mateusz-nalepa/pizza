package pl.tu.kielce.pizza.pantry.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PANTRY")
public class Pantry {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Ingredient> ingredients = new ArrayList<>();
//    private Double flour;
//    private Double cheese;
//    private Double ham;
//    private Double mushrooms;
//    private Double chicken;
//    private Double salami;
//    private Double corn;

}
