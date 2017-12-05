package pl.tu.kielce.pizza.be.security.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.tu.kielce.pizza.be.security.model.jpa.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,
            value = "" +
                    "SELECT u.user_id, u.email, u.name, u.last_name  " +
                    "FROM user u  " +
                    "LEFT JOIN user_role ur ON u.user_id = ur.user_id  " +
                    "LEFT JOIN role r ON ur.role_id = r.role_id  " +
                    "WHERE r.role = 'MANAGER'  " +
                    "AND (select count(*) from user_role where user_id = u.user_id) = 3 " +
                    "AND u.department_id IS NULL ")
    List<Object[]> findFreeManagers();

    @Query(nativeQuery = true,
            value = "" +
                    "SELECT u.user_id, u.email, u.name, u.last_name  " +
                    "FROM user u  " +
                    "LEFT JOIN user_role ur ON u.user_id = ur.user_id  " +
                    "LEFT JOIN role r ON ur.role_id = r.role_id  " +
                    "WHERE r.role = 'USER'  " +
                    "AND (select count(*) from user_role where user_id = u.user_id) = 2 " +
                    "AND u.department_id IS NULL ")
    List<Object[]> findFreeUsers();

    @Query("select u from User u where u.id in :usersIds ")
    List<User> findByUsersIds(@Param("usersIds") List<Long> usersIds);

    @Query("select u from User u where u.department.id = :departmentId ")
    List<User> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("select d.manager from Department d where d.id = :departmentId and d.manager.mainRoleType = pl.tu.kielce.pizza.common.security.enums.MainRoleType.MANAGER")
    User findManagerByDepartmentId(@Param("departmentId") Long departmentId);


    @Modifying
    @Query("update User set avatarLocation = :fileAbsolutePath where id = :userId")
    void updateUserAvatar(@Param("userId") Long userId, @Param("fileAbsolutePath") String fileAbsolutePath);

    @Query("select u.avatarLocation from User  u where u.id = :userId")
    String fetchAvatarLocation(@Param("userId") Long userId);
}



