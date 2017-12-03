package pl.tu.kielce.pizza.fe.department.controller;

import lombok.*;
import pl.tu.kielce.pizza.common.department.dto.FreeUserDto;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUsersToDepartmentDto {

    private List<FreeUserDto> freeUsers;
}
