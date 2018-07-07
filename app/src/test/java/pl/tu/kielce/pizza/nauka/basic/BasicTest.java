package pl.tu.kielce.pizza.nauka.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class BasicTest {


    @Test
    public void clonning() {
//        CustomInteger customInteger = new CustomInteger(1, "1");

//        List<String> numberList = Arrays.asList("number 0", "number 1", "number 2", "number 3", "number 4", "number 5");
//        numberList.add("MATEUSZ");
//        numberList.forEach(System.out::println);


        for (int i=0; i< 10; i++) {
            if (i%2 == 0 ) {
                continue;
            }
            System.out.println(i);
        }
        System.gc();
    }
}


