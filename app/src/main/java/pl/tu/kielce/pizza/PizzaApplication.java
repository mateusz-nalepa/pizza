package pl.tu.kielce.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

}
