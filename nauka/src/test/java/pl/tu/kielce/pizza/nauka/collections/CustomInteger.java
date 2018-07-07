package pl.tu.kielce.pizza.nauka.collections;

import java.util.Objects;

public class CustomInteger implements Cloneable {
    int number;
    String numer;

    @Override
    public boolean equals(Object o) {
//        System.out.println("equals");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomInteger that = (CustomInteger) o;
        return number == that.number &&
                Objects.equals(numer, that.numer);
    }

    @Override
    public int hashCode() {
//        System.out.println("hashCode");
        return Objects.hash(number, numer);
    }

    public CustomInteger(int number) {
        this.number = number;
    }

    public CustomInteger(int number, String numer) {
        this.number = number;
        this.numer = numer;
    }

    @Override
    public String toString() {
        return "CustomInteger{" +
                "number=" + number +
                ", numer='" + numer + '\'' +
                '}';
    }
}
