package pl.tu.kielce.pizza.nauka.collections;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MyOwnListTest {

    @Test
    public void addNewElementToArrayListVsLinkedListOnTheEnd() {
        int numberOfElements = 50_000_000;
        StopWatch watch = new StopWatch();
        List<Integer> integers = new ArrayList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of ArrayList is: " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new LinkedList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of LinkedList is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void addNewElementToArrayListVsLinkedListOnBeginning() {
        int numberOfElements = 50000;
        StopWatch watch = new StopWatch();
        List<Integer> integers = new ArrayList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(0, 5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of ArrayList is: " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new LinkedList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(0, 5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of LinkedList is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void addNewElementToArrayListVsLinkedListOnMiddle() {
        int numberOfElements = 50000;
        StopWatch watch = new StopWatch();
        List<Integer> integers = new ArrayList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(integers.size() / 2, 5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of ArrayList is: " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new LinkedList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(integers.size() / 2, 5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of LinkedList is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void removeFirstElementsFromArrayListVsLinkedList() {
        int numberOfElements = 100000;
        //ArrayList
        List<Integer> integers = createListWithSize(numberOfElements, ArrayList.class);
        int numberOfElementsToRemove = numberOfElements / 2;
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < numberOfElementsToRemove; i++) {
            integers.remove(0);
        }
        watch.stop();
        System.out.println("Time of removing " + numberOfElementsToRemove + " elements from ArrayList with size " + numberOfElements + " elements is: " + watch.getTotalTimeMillis());

        //LinkedList
        integers = createListWithSize(numberOfElements, LinkedList.class);
        numberOfElementsToRemove = numberOfElements / 2;
        watch = new StopWatch();
        watch.start();
        for (int i = 0; i < numberOfElementsToRemove; i++) {
            integers.remove(0);
        }
        watch.stop();
        System.out.println("Time of removing " + numberOfElementsToRemove + " elements from LinkedList with size " + numberOfElements + " elements is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void removeLastElementsFromArrayListVsLinkedList() {
        int numberOfElements = 1000000;
        //ArrayList
        List<Integer> integers = createListWithSize(numberOfElements, ArrayList.class);
        int numberOfElementsToRemove = numberOfElements / 2;
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < numberOfElementsToRemove; i++) {
            integers.remove(integers.size() - 1);
        }
        watch.stop();
        System.out.println("Time of removing " + numberOfElementsToRemove + " elements from ArrayList with size " + numberOfElements + " elements is: " + watch.getTotalTimeMillis());

        //LinkedList
        integers = createListWithSize(numberOfElements, LinkedList.class);
        numberOfElementsToRemove = numberOfElements / 2;
        watch = new StopWatch();
        watch.start();
        for (int i = 0; i < numberOfElementsToRemove; i++) {
            integers.remove(integers.size() - 1);
        }
        watch.stop();
        System.out.println("Time of removing " + numberOfElementsToRemove + " elements from LinkedList with size " + numberOfElements + " elements is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void removeMiddleElementsFromArrayListVsLinkedList() {
        int numberOfElements = 10000;
        //ArrayList
        List<Integer> integers = createListWithSize(numberOfElements, ArrayList.class);
        int numberOfElementsToRemove = numberOfElements / 2;
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < numberOfElementsToRemove; i++) {
            integers.remove(integers.size() / 2);
        }
        watch.stop();
        System.out.println("Time of removing " + numberOfElementsToRemove + " elements from ArrayList with size " + numberOfElements + " elements is: " + watch.getTotalTimeMillis());

        //LinkedList
        integers = createListWithSize(numberOfElements, LinkedList.class);
        numberOfElementsToRemove = numberOfElements / 2;
        watch = new StopWatch();
        watch.start();
        for (int i = 0; i < numberOfElementsToRemove; i++) {
            integers.remove(integers.size() / 2);
        }
        watch.stop();
        System.out.println("Time of removing " + numberOfElementsToRemove + " elements from LinkedList with size " + numberOfElements + " elements is: " + watch.getTotalTimeMillis());
    }

    private <T extends List> List<Integer> createListWithSize(int size, Class<T> clazz) {
        List<Integer> integers = null;
        try {
            integers = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            integers.add(5);
        }
        return integers;
    }

    @Test
    public void addNewElementToLinkedListOnTheBeginningVsEnd() {
        int numberOfElements = 50000;
        StopWatch watch = new StopWatch();
        List<Integer> integers = new ArrayList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(0, 5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to beginning of LinkedList is: " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new LinkedList<>();
        watch.start();
        for (int i = 0; i < numberOfElements; i++) {
            integers.add(5);
        }
        watch.stop();
        System.out.println("Time of adding " + numberOfElements + " elements to end of LinkedList is: " + watch.getTotalTimeMillis());
    }

    @Test
    public void arrayListTestingTimeOfAddingNewElement() {
        int numberOfElements = 1000000;
        StopWatch watch = new StopWatch();
        List<Integer> integers = new ArrayList<>(3);
        watch.start();
        for (int i = 0; i<numberOfElements; i++) {
            integers.add(5);
        }
        watch.stop();
        System.out.println("Total time for add 1000K objects for ArrayList with initial capacity     3:  " + watch.getTotalTimeMillis());

        watch = new StopWatch();
        integers = new ArrayList<>(numberOfElements);
        watch.start();
        for (int i = 0; i<numberOfElements; i++) {
            integers.add(5);
        }
        watch.stop();
        System.out.println("Total time for add 1000K objects for ArrayList with initial capacity 1000K:  " + watch.getTotalTimeMillis());
    }

    @Test
    public void asd() throws NoSuchFieldException, IllegalAccessException {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        List<Integer> integers = new ArrayList<>(10);


        for (int i = 0; i< 100; i++) {
            addAndDisplayNumberOfItems(integers);
        }
    }


    private void addAndDisplayNumberOfItems(List<Integer> integers) throws IllegalAccessException {
        integers.add(2);
        Object[] elementData = (Object[]) FieldUtils.readField(integers, "elementData", true);
        System.out.println(elementData.length);
    }
}
