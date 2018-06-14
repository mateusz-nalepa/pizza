package pl.tu.kielce.pizza.nauka.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.StopWatch;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MyOwnSetTest {

    @Test
    public void addNewElementToHashSetVsTreeSet() {
        int numberOfElements = 500000;
        StopWatch watch = new StopWatch();
        Set<Integer> integers = new HashSet<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(i);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to HashSet is: " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new TreeSet<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(i);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to TreeSet is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void testOrdering() {
        Set<String> strings = new HashSet<>();

        strings.add("Abhijeet");
        strings.add("Ram");
        strings.add("Kevin");
        strings.add("Singh");
        strings.add("Rick");

        System.out.println(strings);

        strings = new TreeSet<>();
        strings.add("Abhijeet");
        strings.add("Ram");
        strings.add("Kevin");
        strings.add("Singh");
        strings.add("Rick");

        System.out.println(strings);
    }

    @Test(expected = ClassCastException.class)
    public void shouldThrowClassCastException() {
        Set<ExampleClass> exampleClasses =  new TreeSet<>();
        exampleClasses.add(new ExampleClass());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException() {
        Set<ExampleClass> exampleClasses =  new TreeSet<>();
        exampleClasses.add(null);
    }

    @Test
    public void shouldAllowForNullElement() {
        Set<ExampleClass> exampleClasses =  new HashSet<>();
        exampleClasses.add(null);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldThrowConcurrentModificationException() {
        Set<Integer> integers =  new HashSet<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        for (Integer integer : integers) {
            integers.add(4);
        }
    }

}

