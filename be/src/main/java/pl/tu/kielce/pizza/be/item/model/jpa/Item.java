package pl.tu.kielce.pizza.be.item.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String description;

    private Double price;

    private boolean active;
}
