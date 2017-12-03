package pl.tu.kielce.pizza.common.security.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;

import java.util.Collection;

@Getter
@ToString
public class UserProfile extends User {


    private DepartmentDto departmentDto;
    private AccountStatus accountStatus;
    private Long id;
    private String email;
    private boolean enabled;
    private Double multiplier;
    private UserDto userDto;

    public UserProfile(
            DepartmentDto departmentDto, UserDto userDto, String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            String email,
            Double multiplier, AccountStatus accountStatus) {
        super(username, password, authorities);
        this.departmentDto = departmentDto;
        this.enabled = enabled;
        this.email = email;
        this.multiplier = multiplier;
        this.accountStatus = accountStatus;
        this.userDto = userDto;
    }

//    public UserProfile(
//            Long id,
//            String username,
//            String password,
//            Collection<? extends GrantedAuthority> authorities,
//            boolean enabled,
//            String email) {
//        this(username, password, authorities, enabled, email);
//        this.id = id;
//    }
}
