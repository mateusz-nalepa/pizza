package pl.tu.kielce.pizza.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tu.kielce.pizza.RunAtStart;

import javax.annotation.PostConstruct;

@Component
public class AnyClass
{
    @Autowired
    private RunAtStart runAtStart;
    
    @PostConstruct
    public void asd() {
        runAtStart.runAtStart();
    }
}
