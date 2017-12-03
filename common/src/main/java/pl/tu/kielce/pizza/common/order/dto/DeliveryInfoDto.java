package pl.tu.kielce.pizza.common.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.common.common.dto.AddressDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfoDto {

    private AddressDto addressDto;
    private DeliveryUserDataDto deliveryUserDataDto;
    private String note;
}
