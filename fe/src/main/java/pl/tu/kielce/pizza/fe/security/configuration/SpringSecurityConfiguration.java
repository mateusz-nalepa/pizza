package pl.tu.kielce.pizza.fe.security.configuration;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.tu.kielce.pizza.common.department.dto.DepartmentDto;
import pl.tu.kielce.pizza.common.department.service.DepartmentService;
import pl.tu.kielce.pizza.common.order.dto.UserOrderDto;
import pl.tu.kielce.pizza.common.order.session.UserContext;
import pl.tu.kielce.pizza.common.security.dto.RoleDto;
import pl.tu.kielce.pizza.common.security.dto.UserDto;
import pl.tu.kielce.pizza.common.security.dto.UserProfile;
import pl.tu.kielce.pizza.common.security.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

//import pl.tu.kielce.pizza.fe.security.controller.CustomFilter;

@Configuration
@RequiredArgsConstructor
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserContext userContext;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final DepartmentService departmentService;



    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {

        auth
//                .authenticationProvider(authProvider)
                .userDetailsService(this::userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @SneakyThrows
    private UserProfile userDetailsService(String email) {

        userContext.setUserOrderDto(new UserOrderDto());
        UserDto userDto = userService.findByEmail(email);
        Set<SimpleGrantedAuthority> authorities = userDto
                .getRoles()
                .stream()
                .map(RoleDto::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());


        Double multiplier = departmentService.multiplier(userDto.getId());
        DepartmentDto departmentDto = departmentService.departmentByUser(userDto.getId());

        userContext.setDepartmentDto(departmentDto);
        userContext.setMultiplier(multiplier);

        if (userDto.getRoles().contains("MANAGER") || userDto.getRoles().contains("USER")) {
            if (departmentDto == null) {
                throw new RuntimeException("ODDZIAL NIE JEST USTAWIONY!!");
            }
        }

        return new UserProfile(departmentDto, userDto, email, userDto.getPassword(), authorities, true, email, multiplier, userDto.getAccountStatus());
    }

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {

        http
//                .addFilterAfter(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new CustomFilter(), FilterSecurityInterceptor.class)
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_LIST).permitAll()
//                .antMatchers("/css/**", "/js/**").permitAll()
//                .antMatchers(PERMIT_CLIENT_LIST).hasAuthority("CLIENT")
                .antMatchers(PERMIT_USER_LIST).hasAuthority("USER")
                .antMatchers(PERMIT_MANAGER_LIST).hasAuthority("MANAGER")
                .antMatchers(PERMIT_ADMIN_LIST).hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
//                .defaultSuccessUrl("/")
//                .successHandler(successHandler)
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
            "/client/**",
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

    private static final String PERMIT_CLIENT_LIST[] = {
//            "/client/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/dyskd/**");
    }

}
