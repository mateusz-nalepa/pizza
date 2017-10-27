package pl.tu.kielce.pizza.security.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.security.model.jpa.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
}
