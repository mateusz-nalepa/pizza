package pl.tu.kielce.pizza.be.order.model.jpa;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.be.item.model.jpa.Item;

import javax.persistence.*;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BoughtItem {//extends Ingredient {

    @ManyToOne
    private Department department;

    @ManyToOne
    private Order order;

    @Id
    @GeneratedValue
    private Long id;

    private Double quantity;

    private Double purchasePrice;

    @OneToOne
    private Item item;

}
