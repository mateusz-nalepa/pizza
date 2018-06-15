package pl.tu.kielce.pizza.nauka.collections;

import java.util.Objects;

public class CustomInteger {
    int number;
    String numer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomInteger that = (CustomInteger) o;
        return number == that.number &&
                Objects.equals(numer, that.numer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, numer);
    }

    public CustomInteger(int number) {
        this.number = number;
    }

}
