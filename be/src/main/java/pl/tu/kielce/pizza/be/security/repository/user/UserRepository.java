package pl.tu.kielce.pizza.be.security.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.tu.kielce.pizza.be.security.model.jpa.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,
            value = "" +
                    "SELECT u.user_id, u.email, u.name, u.last_name FROM user u  " +
                    "LEFT JOIN user_role ur " +
                    "ON u.user_id = ur.user_id " +
                    "LEFT JOIN role r  " +
                    "ON ur.role_id = r.role_id  " +
                    "WHERE r.role = 'MANAGER' " +
                    "AND u.department_id IS NULL")
    List<Object[]> findFreeManagers();
}
