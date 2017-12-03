package pl.tu.kielce.pizza.common.department.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.annotation.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeUserDto  {

    @Column
    private Long id;

    @Column(index = 1)
    private String email;

    @Column(index = 2)
    private String name;

    @Column(index = 3)
    private String lastName;

    private boolean selected;

    public String getLabel() {
        return name + " " + lastName + " (" + email + ")";
    }

}