package pl.tu.kielce.pizza.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.security.model.jpa.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRole(String role);
}
