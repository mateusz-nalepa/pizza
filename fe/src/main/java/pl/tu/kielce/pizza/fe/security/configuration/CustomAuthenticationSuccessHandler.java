package pl.tu.kielce.pizza.fe.security.configuration;

import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import pl.tu.kielce.pizza.common.security.dto.AccountStatus;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

    @SneakyThrows
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication auth) {

        UserProfile asd =  (UserProfile) auth.getPrincipal();


        if (asd.getAccountStatus().equals(AccountStatus.INITIAL)) {
            response.sendRedirect("/changePassword");
        } else {
            target.onAuthenticationSuccess(request, response, auth);
        }
    }

    @SneakyThrows
    public void proceed(HttpServletRequest request,
                        HttpServletResponse response, Authentication auth) {
        target.onAuthenticationSuccess(request, response, auth);
    }
}