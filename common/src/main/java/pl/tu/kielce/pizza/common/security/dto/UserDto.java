package pl.tu.kielce.pizza.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;
import pl.tu.kielce.pizza.common.security.enums.MainRoleType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AuditableEntityDto{

    private String password;
    private String passwordv2;


    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;

    private boolean active;
    private List<RoleDto> roles;// = Collections.emptySet();
    private AccountStatus accountStatus;
    private AddressDto addressDto;
    private MainRoleType mainRoleType;

    private String avatarLocation;

    public String getLabel() {
        return name + " " + lastName + " (" + email + " )";
    }
}
