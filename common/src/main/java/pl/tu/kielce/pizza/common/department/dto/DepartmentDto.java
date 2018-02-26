package pl.tu.kielce.pizza.common.department.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto extends AuditableEntityDto implements SelectItem{

    private AddressDto addressDto;


    private UserDto manager;

    @NotNull(message = "{missingOrWrongValue}")
    private Double multiplier;

    @Pattern(regexp = "[0-9]{9,9}", message = "{validation.phoneNumber}" )
    private String phoneNumber;

    @NotNull(message = "{missingOrWrongValue}")
    private LocalTime openHour;

    @NotNull(message = "{missingOrWrongValue}")
    private LocalTime closeHour;

    public String workHours() {
        return "Czynne od: " + openHour + " do " + closeHour;
    }

    @Override
    public String getLabel() {
//        return addressDto.getCity() + " " + addressDto.getStreet() + " " + addressDto.getHouseNumber() + " " + addressDto.getFlatNumber() + " " + manager.getPhoneNumber();
        return addressDto.getCity() + ", al. " + addressDto.getStreet() + " " + addressDto.getHouseNumber() + "/" + addressDto.getFlatNumber();
    }
}
