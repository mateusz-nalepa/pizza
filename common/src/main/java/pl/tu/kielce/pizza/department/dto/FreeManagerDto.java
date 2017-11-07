package pl.tu.kielce.pizza.department.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.annotation.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeManagerDto {

    @Column
    private Long id;
    @Column(index = 1)
    private String email;
    @Column(index = 2)
    private String name;
    @Column(index = 3)
    private String lastName;

    @Override
    public String toString() {
        return name + " " + lastName + " (" + email + ")";
    }
}
