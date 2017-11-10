package pl.tu.kielce.pizza.be.jpa;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOUGHT_INGREDIENT_DEPARTMENT")
public class BoughtIngredientDepartment extends Ingredient {

    @ManyToOne
    private Department department;

    @ManyToOne
    private Order order;

    private Double price;
//    @Builder
//    public BoughtIngredientDepartment(Item item, Double quantity, Department department, Order order) {
//        super(item, quantity);
//        this.department = department;
//        this.order = order;
//    }
//
//    public static class BoughtIngredientDepartmentBuilder extends IngredientBuilder {
//        BoughtIngredientDepartmentBuilder() {
//            super();
//        }
//    }
}
