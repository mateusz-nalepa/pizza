package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.Ciasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.Ser;

public class Pizza {

    private Ciasto ciasto;
    private Ser ser;

    public Ciasto getCiasto() {
        return ciasto;
    }

    public void setCiasto(Ciasto ciasto) {
        this.ciasto = ciasto;
    }

    public Ser getSer() {
        return ser;
    }

    public void setSer(Ser ser) {
        this.ser = ser;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "ciasto=" + ciasto +
                ", ser=" + ser +
                '}';
    }
}
