package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryUserData {

    private Long deliveryUserDataId;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;

}
