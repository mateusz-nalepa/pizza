package pl.tu.kielce.pizza;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

//@ContextConfiguration(classes = It4emOperationTestContext.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@SqlGroup({
//              @Sql(scripts = "classpath:authenticate/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
//              @Sql(scripts = "classpath:authenticate/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//          })
@RunWith(MockitoJUnitRunner.class)
public class PizzaApplicationTest{
    
    @Test
    public void testNumbers() {
        Assert.assertEquals(5,5);
    }

}
