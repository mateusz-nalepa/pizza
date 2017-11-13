package pl.tu.kielce.pizza.be.pizza.model.jpa;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

@ToString(exclude = "ingredients")
@EqualsAndHashCode(exclude = "ingredients")

public class Pizza {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Double price;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    private boolean active;

    public void activate() {
        this.active = true;
    }

//    @Override
//    public String toString() {
//        return "Pizza{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", ingredients=" + ingredients +
//                ", active=" + active +
//                '}';
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        Pizza pizza = (Pizza) o;
//
//        if (active != pizza.active) return false;
//        if (id != null ? !id.equals(pizza.id) : pizza.id != null) return false;
//        if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;
//        if (description != null ? !description.equals(pizza.description) : pizza.description != null) return false;
//        return price != null ? price.equals(pizza.price) : pizza.price == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (id != null ? id.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + (price != null ? price.hashCode() : 0);
//        result = 31 * result + (active ? 1 : 0);
//        return result;
//    }

    public void deActivate() {
        this.active = false;
    }


//    @ManyToOne
//    private Department department;
}
