package pl.tu.kielce.pizza;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.tu.kielce.pizza.empty.EmptyComponent;
import sun.invoke.empty.Empty;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SqlGroup({
//              @Sql(scripts = "classpath:authenticate/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//              @Sql(scripts = "classpath:authenticate/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//          })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PizzaApplicationTestContext.class)
public class PizzaApplicationTestWithContext
{
    
    @Autowired
    EmptyComponent emptyComponent;
    
    @Test
    public void testNumbers() {
        Assert.assertEquals(emptyComponent.returnFive(), 5);
    }

}
