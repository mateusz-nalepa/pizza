package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizzerie;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.Ciasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.fabrykiSkladnikow.FabrykaSladnikowPizzy;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze.Pizza;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.Ser;

public abstract class Pizzeria {

    FabrykaSladnikowPizzy fabrykaSladnikowPizzy;

    public Pizza zamowPizze(String type) {

        Pizza pizza = stworzPizze(type);

        Ciasto ciasto = fabrykaSladnikowPizzy.stworzCiasto();
        Ser ser = fabrykaSladnikowPizzy.stworzSer();

        pizza.setCiasto(ciasto);
        pizza.setSer(ser);

        return pizza;

    }

    protected abstract Pizza stworzPizze(String type);



}
