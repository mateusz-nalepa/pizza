package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.fabrykiSkladnikow;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.Ciasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.Ser;

public interface FabrykaSladnikowPizzy {

    Ciasto stworzCiasto();
    Ser stworzSer();

}
