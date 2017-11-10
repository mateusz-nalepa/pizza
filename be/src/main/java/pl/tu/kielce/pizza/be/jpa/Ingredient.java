package pl.tu.kielce.pizza.be.jpa;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Item item;

    private Double quantity;

//    Ingredient(Item item, Double quantity) {
//        this.item = item;
//        this.quantity = quantity;
//    }
//
//    public static IngredientBuilder builder() {
//        return new IngredientBuilder();
//    }
//
//    public static class IngredientBuilder {
//        private Item item;
//        private Double quantity;
//
//        IngredientBuilder() {
//        }
//
//        public IngredientBuilder item(Item item) {
//            this.item = item;
//            return this;
//        }
//
//        public IngredientBuilder quantity(Double quantity) {
//            this.quantity = quantity;
//            return this;
//        }
//
//
//
//        public Ingredient build() {
//            return new Ingredient(item, quantity);
//        }
//
//    }

}
