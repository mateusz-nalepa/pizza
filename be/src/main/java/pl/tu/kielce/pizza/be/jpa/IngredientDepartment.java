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
@Table(name = "INGREDIENT_DEPARTMENT")
public class IngredientDepartment extends Ingredient{

    @ManyToOne
    private Department department;

    private Double price;

//    @Builder
//    public IngredientDepartment(Item item, Double quantity, Department department) {
//        super(item, quantity);
//        this.department = department;
//    }
//
//    public static class IngredientDepartmentBuilder extends Ingredient.IngredientBuilder {
//        IngredientDepartmentBuilder() {
//            super();
//        }
//    }
}
