package pl.tu.kielce.pizza.common.queryHandler;

import lombok.SneakyThrows;
import pl.tu.kielce.pizza.common.annotation.Column;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class NativeResultQuerySetHandler {

    public static <T> List<T> resultList(List<Object[]> list, Class<T> t) {
        return list
                .stream()
                .map(objects -> singleResult(objects, t))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public static <T> T singleResult(Object[] objects, Class<T> t) {

        T t1 = t.newInstance();
        Field[] declaredFields = t1.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            int annotation = field.getAnnotation(Column.class).index();

            Object o = objects[annotation];

            if (o instanceof BigInteger) {
                BigInteger bigInteger = (BigInteger) o;
                field.set(t1, bigInteger.longValue());
            }

            if (o instanceof Long) {
                field.set(t1, (Long) o);
            }
            if (o instanceof String) {
                field.set(t1, (String) o);
            }

        }
        return t1;
    }

}
