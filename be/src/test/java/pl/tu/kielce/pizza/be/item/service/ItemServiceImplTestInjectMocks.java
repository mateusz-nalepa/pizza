package pl.tu.kielce.pizza.be.item.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tu.kielce.pizza.be.item.repository.ItemExecutor;
import pl.tu.kielce.pizza.common.common.util.NewPriceContextUtils;
import pl.tu.kielce.pizza.common.item.dto.ItemDto;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTestInjectMocks
{
    
    @Mock
    private NewPriceContextUtils newPriceContextUtils;
    
    @Mock
    private ItemExecutor itemExecutor;
    
    @InjectMocks
    private ItemServiceImpl itemService;
    
    @Test
    public void testInjectMocks()
    {
        System.out.println(newPriceContextUtils);
    }
    
    @Test
    public void testNumberOfInvocations()
    {
        //given
        when(itemExecutor.findOne(any(Long.class))).thenReturn(exampleItem());
        
        //when
        ItemDto one = itemService.findOne(1L);
        
        //then
        verify(itemExecutor, times(1)).findOne(any(Long.class));
        verify(newPriceContextUtils, times(1)).priceWithMultiplier(anyDouble());
    }
    
    private ItemDto exampleItem()
    {
        return ItemDto
            .builder()
            .id(1L)
            .name("Pepsi")
            .price(5D)
            .build();
    }
    
    @Test
    public void testReturningAnotherValueAfterEveryExecution()
    {
        //TODO finsh that!
        //given
//        when(itemExecutor.returnString(any()))
//            .thenReturn(1, 2, 3);
        
        //when
//        itemService.returnAnotherValueOnExecution();
        
        //then
        Assert.assertTrue(true);
    }
    
    @SuppressWarnings("unchecked")
    @Test(expected = RuntimeException.class)
    public void testThrowingException()
    {
        //given
        when(itemExecutor.findOne(any(Long.class))).thenThrow(RuntimeException.class);
        
        //when
        ItemDto one = itemService.findOne(1L);
        
    }
    
    @Test
    public void testCallRealMethod()
    {
        //given
        when(itemExecutor.returnString(any(String.class)))
            .thenReturn("wartość z testu")
            .thenCallRealMethod()
            .thenReturn("znowu test");
        
        //when
        itemService.returnAnotherValueOnExecution();
        
        //then
        assertTrue(true);
    }
    
    @Test
    public void testCallAnswer()
    {
        
        //Answer pozwala robić modyfikacje na parametrze, z jakim się wykonuje metoda!
        //given
        when(itemExecutor.returnString(any(String.class)))
            .thenReturn("wartość z testu")
            .thenCallRealMethod()
            .thenAnswer(invocation -> invocation.getArguments()[0]);
        
        //when
        itemService.returnAnotherValueOnExecution();
        
        //then
        assertTrue(true);
    }
    
    @Test(expected = RuntimeException.class)
    public void testDoThrow()
    {
    
        //given
        doThrow(RuntimeException.class)
            .when(itemExecutor)
            .voidMethod();
        
        //when
        itemService.voidMethod();
        
    }
}
