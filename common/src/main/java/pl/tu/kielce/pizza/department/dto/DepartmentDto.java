package pl.tu.kielce.pizza.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.dto.AddressDto;
import pl.tu.kielce.pizza.security.dto.UserDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto  {

    private Long id;

    private AddressDto address;

//    private PantryDto pantry;
    private UserDto manager;
//    private List<User> employees;
//    private List<Pizza> pizzas;

    private Double multiplier;
//    private FreeManagerDto freeManager;
    private boolean active;
}
