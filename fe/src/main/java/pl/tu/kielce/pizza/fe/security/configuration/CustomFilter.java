package pl.tu.kielce.pizza.fe.security.configuration;

import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import pl.tu.kielce.pizza.common.security.dto.AccountStatus;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;
import pl.tu.kielce.pizza.common.security.util.UserUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomFilter extends GenericFilterBean {

    private static final String CHANGE_PASSWORD = "/changePassword";

    @Override
    @SneakyThrows
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String requestURI = servletRequest.getRequestURI();

        if (isNotChangePasswordUri(request) && userHasToChangePassword()) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(CHANGE_PASSWORD);
            return;
        }

        chain.doFilter(request, response);

    }

    private boolean isNotChangePasswordUri(ServletRequest request) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String requestURI = servletRequest.getRequestURI();

        return !requestURI.equals(CHANGE_PASSWORD);
    }

    private boolean userHasToChangePassword() {

        boolean notLogged = UserUtils.isNotLogged();

        if (UserUtils.isNotLogged()) {
            return false;
        }

        boolean client = UserUtils.isClient();
        if (UserUtils.isClient()) {
            return false;
        }

        UserProfile userProfile = (UserProfile) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userProfile.getAccountStatus().equals(AccountStatus.INITIAL);
    }
}
