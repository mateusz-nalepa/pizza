package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.order.model.jpa.enums.orderstatus.OrderStatus;
import pl.tu.kielce.pizza.be.order.model.jpa.enums.ordertype.OrderType;
import pl.tu.kielce.pizza.be.security.model.jpa.User;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_TABLE")
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<BoughtItem> ingredientDepartments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<BoughtPizza> pizzas;

    @OneToOne(mappedBy = "order")
    private DeliveryAddress deliveryAddress;

    @Enumerated
    private OrderType orderType;

    @Enumerated
    private OrderStatus orderStatus;


    private Double totalPrice;
}
