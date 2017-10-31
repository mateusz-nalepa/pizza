package pl.tu.kielce.pizza.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;
    private String email;
    private String name;
    private String password;
    private String lastName;
    private boolean active;
    private Set<RoleDto> roles = Collections.emptySet();
}
