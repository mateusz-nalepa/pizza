package pl.tu.kielce.pizza.fe.security.configuration;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;
import pl.tu.kielce.pizza.common.security.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final DepartmentService departmentService;

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {

        auth
                .userDetailsService(this::userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    private UserProfile userDetailsService(String email) {
        UserDto userByEmail = userService.findByEmail(email);
        Set<SimpleGrantedAuthority> authorities = userByEmail
                .getRoles()
                .stream()
                .map(RoleDto::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        Double multiplier = departmentService.multiplier(userByEmail.getId());
        return new UserProfile(email, userByEmail.getPassword(), authorities, true, email, multiplier);
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {

        http.
                authorizeRequests()
                .antMatchers(PERMIT_ALL_LIST).permitAll()
                .antMatchers(PERMIT_USER_LIST).hasAuthority("USER")
                .antMatchers(PERMIT_MANAGER_LIST).hasAuthority("MANAGER")
                .antMatchers(PERMIT_ADMIN_LIST).hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/admin/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    private static final String PERMIT_ALL_LIST[] = {
            "/",
            "/login/**",
            "/registration/**",
            "/language/**",
            "/h2/**"
    };

    private static final String PERMIT_ADMIN_LIST[] = {
            "/admin/**"
    };

    private static final String PERMIT_MANAGER_LIST[] = {
            "/manager/**"
    };

    private static final String PERMIT_USER_LIST[] = {
            "/user/**"
    };

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//    }

}