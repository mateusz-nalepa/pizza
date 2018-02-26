package pl.tu.kielce.pizza.common.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "{missingOrWrongValue}")
    private String city;

    @NotNull(message = "{missingOrWrongValue}")
    private String street;

    @NotNull(message = "{missingOrWrongValue}")
    private Integer houseNumber;

    private Integer flatNumber;

//    private String name;
//    private String lastName;
//    private String phoneNumber;
//    private String email;
//
//    private String note;
}
