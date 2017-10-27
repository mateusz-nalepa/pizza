package pl.tu.kielce.pizza.security.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tu.kielce.pizza.security.model.jpa.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRole(String role);
}
