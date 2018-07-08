package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizzerie;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.fabrykiSkladnikow.AmerykanskaFabrykaSkladnikow;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze.AmerykanskaSerowaPizza;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.pizze.Pizza;

public class AmerykanskaPizzeria extends Pizzeria{

    public AmerykanskaPizzeria() {
        this.fabrykaSladnikowPizzy = new AmerykanskaFabrykaSkladnikow();
    }

    @Override
    protected Pizza stworzPizze(String type) {

        if ("serowa".equals(type)) {
            return new AmerykanskaSerowaPizza();
        }

        throw new RuntimeException();
    }
}
