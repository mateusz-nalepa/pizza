package pl.tu.kielce.pizza.common.model.jpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private Integer streetNumber;
    private Integer houseNumber;

}
