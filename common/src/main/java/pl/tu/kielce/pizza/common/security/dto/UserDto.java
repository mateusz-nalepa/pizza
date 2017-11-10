package pl.tu.kielce.pizza.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.dto.AuditableEntityDto;

import java.util.Collections;
import java.util.Set;

@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AuditableEntityDto{

    private Long id;
    private String email;
    private String name;
    private String password;
    private String lastName;
    private boolean active;
    private Set<RoleDto> roles = Collections.emptySet();
}
