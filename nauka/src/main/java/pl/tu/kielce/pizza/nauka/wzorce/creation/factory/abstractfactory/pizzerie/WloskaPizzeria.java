package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizzerie;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.fabrykiSkladnikow.WloskaFabrykaSkladnikow;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze.Pizza;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze.WloskaSerowaPizza;

public class WloskaPizzeria extends Pizzeria{


    public WloskaPizzeria() {
        this.fabrykaSladnikowPizzy = new WloskaFabrykaSkladnikow();
    }

    @Override
    protected Pizza stworzPizze(String type) {

        if ("serowa".equals(type)) {
            return new WloskaSerowaPizza();
        }

        throw new RuntimeException();
    }

}
