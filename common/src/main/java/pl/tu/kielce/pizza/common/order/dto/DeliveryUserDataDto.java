package pl.tu.kielce.pizza.common.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUserDataDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;


}
