package pl.tu.kielce.pizza.ingredient.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String name;

    private String description;

}
