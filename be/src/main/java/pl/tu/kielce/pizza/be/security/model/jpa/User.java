package pl.tu.kielce.pizza.be.security.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import pl.tu.kielce.pizza.be.common.jpa.Address;
import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.be.jpa.Order;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@DynamicUpdate
public class User extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Email(message = "*Please provide a valid Email")
    private String email;

    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders;

//    @ManyToOne
//    private User user;
//
//    @OneToMany(mappedBy="user")
//    @Builder.Default
//    private Set<User> users = new HashSet<>();

    @Embedded
    private Address address;

}
