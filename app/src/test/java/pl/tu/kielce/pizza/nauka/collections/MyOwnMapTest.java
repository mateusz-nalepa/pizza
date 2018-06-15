package pl.tu.kielce.pizza.nauka.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MyOwnMapTest {


    @Test
    public void valueAreUpdated() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("1", "1");
        stringMap.put("1", "2");
        stringMap.put("1", "3");

        Assert.assertEquals("3", stringMap.get("1"));
    }

    @Test
    public void checkNumberOfInternalTable() {
        Map<CustomInteger, String> stringMap = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            addElementAndDisplaySize(stringMap, i);
        }
        // internal table which holds data is equal to 32,
//        cause initial is 16 and is doubled what limit is exceeded
    }

    private void addElementAndDisplaySize(Map<CustomInteger, String> stringMap, Integer s) {
        stringMap.put(new CustomInteger(s), s.toString());
    }
    @Test
    public void checkEqualsAndHashCode() {
        Map<CustomInteger, String> stringMap = new HashMap<>();
        stringMap.put(new CustomInteger(1), "2");
        stringMap.put(new CustomInteger(1), "2");
        stringMap.put(new CustomInteger(1), "2");

        System.out.println("TEraz nastapi GET");
        String s = stringMap.get(new CustomInteger(1));
    }

    @Test
    public void putNewElementsToHashMapVsTreeMap() {
        int numberOfElements = 50_000_00;
        StopWatch watch = new StopWatch();
        Map<Integer, Integer> integers = new HashMap<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.put(i, i);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to HashMap is: " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new TreeMap<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.put(i, i);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to TreeMap is: " + watch.getTotalTimeMillis());
    }
}
