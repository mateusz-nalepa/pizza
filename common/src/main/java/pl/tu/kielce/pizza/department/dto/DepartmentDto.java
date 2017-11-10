package pl.tu.kielce.pizza.department.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.dto.AuditableEntityDto;
import pl.tu.kielce.pizza.security.dto.UserDto;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto extends AuditableEntityDto{

    private AddressDto addressDto;

    private UserDto manager;

//    private List<User> employees;
//
//    private List<IngredientDepartment> ingredientDepartments;
//
//    private List<Pizza> pizzas;

    private Double multiplier;
}
