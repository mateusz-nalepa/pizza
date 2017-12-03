package pl.tu.kielce.pizza.common.department.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

import javax.validation.constraints.NotNull;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto extends AuditableEntityDto implements SelectItem{

    private AddressDto addressDto;

    private UserDto manager;

//    private List<User> employees;
//
//    private List<IngredientDepartment> ingredientDepartments;
//
//    private List<Pizza> boughtPizzas;

    @NotNull(message = "{missingOrWrongValue}")
    private Double multiplier;

    @Override
    public String getLabel() {
        return addressDto.getCity() + " " + addressDto.getStreet() + " " + addressDto.getHouseNumber() + " " + addressDto.getFlatNumber();
    }
}
