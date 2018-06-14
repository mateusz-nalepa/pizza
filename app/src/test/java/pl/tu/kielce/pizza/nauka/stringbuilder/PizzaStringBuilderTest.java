package pl.tu.kielce.pizza.nauka.stringbuilder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PizzaStringBuilderTest
{
    @Test
    public void shouldHaveLength5()
    {
        StringBuilder sb = new StringBuilder("HODOR");
        
        Assert.assertEquals(sb.length(), 5);
    }
    
    @Test
    public void shouldHaveCapacity16()
    {
        StringBuilder sb = new StringBuilder();
        
        Assert.assertEquals(sb.capacity(), 16);
    }
    
    @Test
    public void shouldHaveCapacity21()
    {
        StringBuilder sb = new StringBuilder("Hodor");
        
        Assert.assertEquals(sb.capacity(), 21);
    }
    
    @Test
    public void shouldHaveCapacity34()
    {
    
//        od 17 do 33 to i tak CAPACITY będzie 34, to tylko tak dziwnie w przypadku metody ensureCapacity!!
        StringBuilder sb = new StringBuilder();
        sb.ensureCapacity(33);
        
        Assert.assertEquals(34, sb.capacity());
    }

    @Test
    public void shouldHaveCapacity35()
    {
        StringBuilder sb = new StringBuilder(18);
        Assert.assertEquals(18, sb.capacity());
    }

    @Test
    public void shouldHaveCapacity36()
    {
        StringBuilder sb = new StringBuilder(18);
        sb.ensureCapacity(15);
        Assert.assertEquals(18, sb.capacity());
    }
}
