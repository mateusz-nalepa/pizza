package pl.tu.kielce.pizza.be.pizza.model.jpa;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@ToString(exclude = "boughtPizzas")
@EqualsAndHashCode(exclude = "boughtPizzas")
public class Ingredient {

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Pizza> pizzas;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

//    @Override
//    public String toString() {
//        return "Ingredient{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Ingredient that = (Ingredient) o;
//
//        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        return description != null ? description.equals(that.description) : that.description == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (id != null ? id.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        return result;
//    }
//
//        @Builder
//    public Ingredient(Item item, Double quantity, Pizza pizza) {
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
