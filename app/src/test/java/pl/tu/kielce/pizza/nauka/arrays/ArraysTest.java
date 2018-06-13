package pl.tu.kielce.pizza.nauka.arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ArraysTest
{
    
    @Test
    public void shouldHave2RowsAnd3Columns()
    {
        
        //NAJPIERW WIERSZ, POTEM KOLUMNA!
        double threeRowsTwoColumns[][] = new double[3][2];
        
        double[][] doubleArray = new double[][]{
            {1.1, 1.2, 1.3},
            {2.1, 2.2, 2.3}
        };
        
        Assert.assertEquals(2, doubleArray.length);
        Assert.assertEquals(3, doubleArray[0].length);
        Assert.assertEquals(3, doubleArray[1].length);
    }
    
    @Test
    public void shouldHave5inFirstRowAndSecondCell()
    {
        
        double[][] doubleArray = new double[][]{
            {1.1, 1.2, 1.3},
            {2.1, 2.2, 5}
        };
        
        Assert.assertEquals(5D, doubleArray[1][2], 0.0D);
    }
    
    @Test
    public void shouldHaveZeroRowLength2AndFirstRowLength3()
    {
        
        double[][] doubleArray = new double[][]{
            {1.1, 1.3},
            {2.1, 2.2, 5}
        };
        
        Assert.assertEquals(2, doubleArray[0].length);
        Assert.assertEquals(3, doubleArray[1].length);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowArrayIndexOutOfBoundsException()
    {
        
        double[][] doubleArray = new double[][]{
            {1.1, 1.3},
            {2.1, 2.2, 5}
        };
        
        Assert.assertEquals(2, doubleArray[0][2], 0.0);
    }
    
    @Test
    public void shouldLookLikeHalfPyramid()
    {
        
        int[][] intArray = new int[10][];
        
        for(int i = 0; i < intArray.length; i++)
        {
            intArray[i] = new int[i];
        }
        
        for(int i = 0; i < intArray.length; i++)
        {
            for(int j = 0; j < intArray[i].length; j++)
            {
                intArray[i][j] = new Random().nextInt(6);
//                System.out.printf("%d", intArray[i][j]);
            }
//            System.out.println();
        }
        
        Assert.assertEquals(10, intArray.length);
        Assert.assertEquals(0, intArray[0].length);
        Assert.assertEquals(intArray.length - 1, intArray[intArray.length - 1].length);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowArrayIndexOutOfBoundsExceptionCauseLengthAsRowNumber()
    {
        
        int[][] intArray = new int[2][2];
        Assert.assertEquals(5, intArray[intArray.length].length);
    }

    @Test
    public void compilationFails() {
//        Hashtable //synchronized
//        HashMap //not synchronized
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);



        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("asd", "asd");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(5);
        numbers.add(5);

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            numbers.add(50);
        }

        String[][]strs = new String[5][];
        System.out.println(strs.length);

    }
    
}
