package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.fabrykiSkladnikow;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.Ciasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.impl.NormalneCiasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.NormalnySer;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.Ser;

public class WloskaFabrykaSkladnikow implements FabrykaSladnikowPizzy {
    @Override
    public Ciasto stworzCiasto() {
        return new NormalneCiasto();
    }

    @Override
    public Ser stworzSer()
    {
        return new NormalnySer();
    }
}
