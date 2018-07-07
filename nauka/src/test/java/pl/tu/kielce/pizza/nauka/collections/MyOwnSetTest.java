package pl.tu.kielce.pizza.nauka.collections;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@Scope()
@Component
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

    @Test
    public void testOnlyHashCodeWithNewObjectEveryTime() {
        Set<OnlyHashCode> onlyHashCodes = new HashSet<>();
        onlyHashCodes.add(new OnlyHashCode(1));
        onlyHashCodes.add(new OnlyHashCode(1));
        onlyHashCodes.add(new OnlyHashCode(1));

        Assert.assertEquals(3, onlyHashCodes.size());
        //każdy obiekt ma inny hashCode, dlatego są 3 "takie same"
    }

    @Test
    public void testOnlyHashCodeWithSameObjectEveryTime() {
        Set<OnlyHashCode> onlyHashCodes = new HashSet<>();
        OnlyHashCode onlyHashCode = new OnlyHashCode(1);
        onlyHashCodes.add(onlyHashCode);
        onlyHashCodes.add(onlyHashCode);
        onlyHashCodes.add(onlyHashCode);

        Assert.assertEquals(1, onlyHashCodes.size());
        //cały czas taki sam hashCode, dlatego jest jeden
    }

    @Test
    public void testOnlyEqualsWithNewObjectEveryTime() {
        Set<OnlyEquals> onlyHashCodes = new HashSet<>();
        onlyHashCodes.add(new OnlyEquals(1));
        onlyHashCodes.add(new OnlyEquals(1));
        onlyHashCodes.add(new OnlyEquals(1));

        Assert.assertEquals(3, onlyHashCodes.size());
        //każdy obiekt ma inny hashCode, dlatego są 3 "takie same"
        //  equals 0 razy, bo hashCode inny, więc nie ma sensu patrzeć na equals
    }

    @Test
    public void testOnlyEqualsWithSameObjectEveryTime() {
        Set<OnlyEquals> onlyHashCodes = new HashSet<>();
        OnlyEquals onlyEquals = new OnlyEquals(1);
        onlyHashCodes.add(onlyEquals);
        onlyHashCodes.add(onlyEquals);
        onlyHashCodes.add(onlyEquals);

        Assert.assertEquals(1, onlyHashCodes.size());
        //każdy obiekt ma taki sam hashCode, dlatego jest tylko 1 obiekt
        //  equals 0 razy, bo hashCode cały czas taki sam, więc nie ma sensu patrzeć na equals
    }

    @Test
    public void testSimpleNumberAndChangeValueEveryTime() {
        Set<SimpleNumber> onlyHashCodes = new HashSet<>();
        SimpleNumber simpleNumber = new SimpleNumber(1);
        onlyHashCodes.add(simpleNumber);
        simpleNumber.setNumber(2);
        onlyHashCodes.add(simpleNumber);
        simpleNumber.setNumber(3);
        onlyHashCodes.add(simpleNumber);

        Assert.assertEquals(1, onlyHashCodes.size());
        // cause hashCode return still the same value
    }

    @Test
    public void asd() {
        OnlyEquals one = new OnlyEquals(1);
        OnlyEquals two = new OnlyEquals(1);

        System.out.println("HashCode");
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());

        System.out.println("Equals");
        System.out.println(one.equals(two));
    }

}

class SimpleNumber {
    int number;

    public SimpleNumber(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

class OnlyHashCode{
    int number;

    public OnlyHashCode(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {

        return Objects.hash(number);
    }
}

class OnlyEquals{

    int number;

    public OnlyEquals(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnlyEquals that = (OnlyEquals) o;
        return number == that.number;
    }

}

