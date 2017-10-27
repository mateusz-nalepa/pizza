package pl.tu.kielce.pizza.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.dto.user.UserProfile;
import pl.tu.kielce.pizza.security.service.UserService;

import java.util.Objects;

//@Component
//@RequiredArgsConstructor
public class CustomAuthenticationProvider {//implements AuthenticationProvider {

//    private final UserService userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//        String username = token.getName();
//        String password = token.getCredentials().toString(); // retrieve the password
//        // do something here
//
//        UserProfile login = userService.login(username, password);
//
//        if (Objects.nonNull(login)) {
//            return new UsernamePasswordAuthenticationToken(username, password, login.getAuthorities());
//        }
//
//        return null;
//
//        // if ok then return the authentication
//
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return false;
//    }
}
