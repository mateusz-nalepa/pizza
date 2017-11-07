package pl.tu.kielce.pizza.security.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import pl.tu.kielce.pizza.common.model.jpa.Address;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {


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

//    @ManyToOne
//    private User user;
//
//    @OneToMany(mappedBy="user")
//    @Builder.Default
//    private Set<User> users = new HashSet<>();

    @Embedded
    private Address address;

    private boolean active;

    public void activate() {
        this.active = true;
    }

    public void deActivate() {
        this.active = false;
    }

}
