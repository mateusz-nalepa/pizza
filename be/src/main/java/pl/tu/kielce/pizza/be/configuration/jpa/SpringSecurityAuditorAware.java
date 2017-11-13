package pl.tu.kielce.pizza.be.configuration.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        Optional<Object> optionalPrincipal = Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal);


        if (optionalPrincipal.isPresent()) {
            Object o = optionalPrincipal.get();
            if (o instanceof UserProfile) {
                return ((UserProfile) o).getEmail();
            }
        }

        return "SYSTEM";
    }
}

