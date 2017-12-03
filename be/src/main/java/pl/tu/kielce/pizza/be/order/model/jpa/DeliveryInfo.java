package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.common.jpa.Address;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class DeliveryInfo {

    @Embedded
    private Address address;

    @Embedded
    private DeliveryUserData deliveryUserData;

    private String note;
}
