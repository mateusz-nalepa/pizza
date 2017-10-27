package pl.tu.kielce.pizza.dto.user;

import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class UserDto {

    private int id;
    private String email;
    private String name;
    private String password;
    private String lastName;
    private boolean active;
    private Set<RoleDto> roles;
}
