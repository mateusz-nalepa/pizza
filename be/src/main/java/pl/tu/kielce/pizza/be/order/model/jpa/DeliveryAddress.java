package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class DeliveryAddress {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private Integer flatNumber;
    private Integer houseNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Order order;
}
