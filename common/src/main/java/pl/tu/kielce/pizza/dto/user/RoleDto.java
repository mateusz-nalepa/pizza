package pl.tu.kielce.pizza.dto.user;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    private String role;

}
