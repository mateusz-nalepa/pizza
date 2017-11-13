package pl.tu.kielce.pizza.common.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserUtils {

    public static Double getMultiplier() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(UserProfile.class::cast)
                .map(UserProfile::getMultiplier)
                .orElse(1D);
    }

    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null) {
            return false;
        }
        List<String> admin = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(s -> s.equals("ADMIN"))
                .collect(Collectors.toList());

        return admin.size() == 1;
    }

    public static boolean isNotAdmin() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication == null || !isAdmin();
    }
}
