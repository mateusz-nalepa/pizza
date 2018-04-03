package pl.tu.kielce.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class PizzaApplication
{
    @Autowired
    private RunAtStart runAtStart;
    
    public static void main(String[] args)
    {
        ConfigurableApplicationContext run = SpringApplication.run(PizzaApplication.class, args);
    
//        System.out.println(run);
    }
    
}
