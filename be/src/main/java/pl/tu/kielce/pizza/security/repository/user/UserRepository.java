package pl.tu.kielce.pizza.security.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.security.model.jpa.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

//    @Query("select u " +
//            "from User u " +
//            "where u.active = true " +
//            "and " +
//            " :role MEMBER OF u.roles " +
//            "and " +
//            " u.id not in (select d.id from Department d)")

//    @Query("SELECT u FROM User u WHERE u.id IN (SELECT ur. FROM Role ur WHERE ur.name = :role)")
//    List<User> findAllFreeManagers(@Param("role") String role);

    @Query(nativeQuery = true,
            value = "select u.user_id, u.name, u.last_name, u.email from user u " +
                    "LEFT JOIN user_role ur " +
                    "ON u.user_id = ur.user_id " +
                    "LEFT JOIN role r " +
                    "ON ur.role_id = r.role_id " +
                    "WHERE r.role = :role " +
                    "and u.user_id not in  " +
                    "(SELECT d.manager_user_id  " +
                    "from department d " +
                    "LEFT JOIN User u " +
                    "ON d.manager_user_id = u.user_id " +
                    "WHERE d.manager_user_id is not null)")
    List<Object[]> findAllFreeManagers(@Param("role") String role);
}
