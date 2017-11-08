package pl.tu.kielce.pizza.ingredient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.annotation.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeItemDto {

    @Column
    private Long id;
    @Column(index = 1)
    private String name;
    @Column(index = 2)
    private String description;


    @Override
    public String toString() {
        return name;
    }
}
