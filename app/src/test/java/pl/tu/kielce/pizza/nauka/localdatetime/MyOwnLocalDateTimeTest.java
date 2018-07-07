package pl.tu.kielce.pizza.nauka.localdatetime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class MyOwnLocalDateTimeTest
{
    
    @Test
    public void shouldEqualLastDayOfFebrauary()
    {
        
        //given
        LocalDate lastJanuary = LocalDate.of(2018, 1, 31);
        
        //when
        LocalDate lastFebruary = lastJanuary.plusMonths(1);
        
        //then
        Assert.assertEquals(LocalDate.of(2018, 2, 28), lastFebruary);
    }
    
}
