package pl.tu.kielce.pizza.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.tu.kielce.pizza.dto.user.UserProfile;
import pl.tu.kielce.pizza.security.error.MyAccessDeniedHandler;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.model.jpa.User;
import pl.tu.kielce.pizza.security.service.UserServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
// http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
// Switch off the Spring Boot security configuration
//@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {//implements UserDetailsService{

    private final MyAccessDeniedHandler accessDeniedHandler;
//    private final DataSource dataSource;
//    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private DataSource dataSource;

//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.userDetailsService(s -> {
            User userByEmail = userService.findByEmail(s);
            Set<SimpleGrantedAuthority> authorities = userByEmail
                    .getRoles()
                    .stream()
                    .map(Role::getRole)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            return new UserProfile(s, userByEmail.getPassword(), authorities, true, s);
        }).passwordEncoder(bCryptPasswordEncoder);
    }


//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//
////        auth
////                .jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery(
////                        "select username,password, enabled from users where username=?"
////                )
////                .authoritiesByUsernameQuery(
////                        "select username, role from user_roles where username=?"
////                );
//
//        auth.authenticationProvider(customAuthenticationProvider);
//    }
//
//    // roles admin allow to access /admin/**
//    // roles user allow to access /user/**
//    // custom 403 access denied handler
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/about", "/registration/**", "/language/**").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");
//    }

//    @Override
//    @SneakyThrows
//    public UserProfile loadUserByUsername(String userName) throws UsernameNotFoundException {
//        return userService.login(userName);
//    }

//    public UserProfile login(String userName, ) throws UsernameNotFoundException {
//        return userService.login(userName);
//    }

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return null;
//    }

    /*
    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }*/

//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    @Value("${spring.datasource.password}")
//    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
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

}
