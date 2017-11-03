package pl.tu.kielce.pizza.pizza.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PIZZA")
public class Pizza {

    @Id
    @GeneratedValue
    private Long id;
    private Double flour;
    private Double cheese;
    private Double ham;
    private Double mushrooms;
    private Double chicken;
    private Double salami;
    private Double corn;

    private String name;
    private String description;
    private Double price;


}
