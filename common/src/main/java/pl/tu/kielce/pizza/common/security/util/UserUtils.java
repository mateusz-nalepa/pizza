package pl.tu.kielce.pizza.common.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserUtils {


    public static Long getUserId() {
        return getUserDto()
                .map(UserDto::getId)
                .orElse(null);
    }

    public static boolean isAnonymouse() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof String) {
                String role = (String) authentication.getPrincipal();
                if (role.equals("anonymousUser")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Optional<UserProfile> getUserProfile() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(o -> (UserProfile) o);
    }

    public static String getUserEmail() {
        return getUserProfile()
                .map(UserProfile::getEmail)
                .orElse("");
    }

    public static boolean isLogged() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserProfile) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotLogged() {
        return !isLogged();
    }

    public static boolean isAuthEmpty() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication == null;
    }

    public static Double getMultiplier() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(UserProfile.class::cast)
                .map(UserProfile::getMultiplier)
                .orElse(0D);
    }

    public static Long departmentId() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(UserProfile.class::cast)
                .map(UserProfile::getDepartmentDto)
                .map(DepartmentDto::getId)
                .orElse(null);
    }

    public static DepartmentDto getDepartment() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserProfile) {
                UserProfile userProfile = (UserProfile) principal;
                return userProfile.getDepartmentDto();
            }
        }

        return null;
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

    public static boolean hasRole(String role) {
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
                .filter(s -> s.equals(role))
                .collect(Collectors.toList());

        return admin.size() == 1;
    }

    public static boolean isClient() {
//        Authentication auth = SecurityContextHolder
//                .getContext()
//                .getAuthentication();
//
//        if (auth == null) {
//            return false;
//        }
//
//        List<String> roles = auth.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        return roles.size() == 1 && roles.contains("CLIENT");
        return hasRole("CLIENT");
    }

    public static boolean isNotAdmin() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication == null || !isAdmin();
    }

    public static Optional<UserDto> getUserDto() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth != null) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserProfile) {
                UserProfile userProfile = (UserProfile) principal;
                return Optional.of(userProfile.getUserDto());
            }
        }

        return Optional.empty();
    }

    public static boolean isUser() {
        return hasRole("USER");
    }

    public static boolean isManager() {
        return hasRole("MANAGER");
    }
}
