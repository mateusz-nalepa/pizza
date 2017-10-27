package pl.tu.kielce.pizza.dto.user;

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
//    private String username;
    private boolean enabled;

    public UserProfile(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            String email) {
        super(username, password, authorities);
        this.enabled = enabled;
        this.email = email;
    }

    public UserProfile(
            Long id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            String email) {
        this(username, password, authorities, enabled, email);
        this.id = id;
    }
}
