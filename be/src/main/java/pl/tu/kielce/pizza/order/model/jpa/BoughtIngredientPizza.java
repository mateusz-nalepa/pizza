package pl.tu.kielce.pizza.order.model.jpa;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.ingredient.model.jpa.Ingredient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOUGHT_INGREDIENT_PIZZA")
public class BoughtIngredientPizza extends Ingredient {

    @ManyToOne
    private BoughtPizza boughtPizza;

//    @Builder
//    public IngredientPizza(Item item, Double quantity, Pizza pizza) {
//        super(item, quantity);
//        this.pizza = pizza;
//    }
//
//    public static class IngredientPizzaBuilder extends Ingredient.IngredientBuilder {
//        IngredientPizzaBuilder() {
//            super();
//        }
//
//    }
}
