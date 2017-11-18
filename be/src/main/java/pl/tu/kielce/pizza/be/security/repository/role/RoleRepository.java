package pl.tu.kielce.pizza.be.security.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.be.security.model.jpa.Role;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByRole(String role);

    @Query("select r from Role r where r.id in :roleIds ")
    Set<Role> findByRolesIds(@Param("roleIds") List<Long> roleIds);
}
