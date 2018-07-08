package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.fabrykiSkladnikow;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.Ciasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ciasto.impl.GrubeCiasto;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.PodwojnySer;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser.Ser;

public class AmerykanskaFabrykaSkladnikow implements FabrykaSladnikowPizzy {
    @Override
    public Ciasto stworzCiasto() {
        return new GrubeCiasto();
    }

    @Override
    public Ser stworzSer()
    {
        return new PodwojnySer();
    }
}
