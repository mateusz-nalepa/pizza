package pl.tu.kielce.pizza.common.security.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@ToString
public class UserProfile extends User {

    private Long id;
    private String email;
    private boolean enabled;
    private Double multiplier;

    public UserProfile(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            String email,
            Double multiplier) {
        super(username, password, authorities);
        this.enabled = enabled;
        this.email = email;
        this.multiplier = multiplier;
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