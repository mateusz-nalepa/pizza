package pl.tu.kielce.pizza.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.model.jpa.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
}
