package pl.tu.kielce.pizza.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;
import pl.tu.kielce.pizza.common.security.enums.MainRoleType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AuditableEntityDto{

    private Long id;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String lastName;
    private boolean active;
    private List<RoleDto> roles;// = Collections.emptySet();
    private AccountStatus accountStatus;
    private AddressDto addressDto;
    private MainRoleType mainRoleType;

}
