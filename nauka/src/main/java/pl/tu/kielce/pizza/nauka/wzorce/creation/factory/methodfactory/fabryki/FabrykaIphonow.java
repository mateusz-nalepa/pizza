package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.fabryki;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.Iphone5s;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.Iphone7;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.Phone;

public class FabrykaIphonow extends FabrykaTelefonow {
    @Override
    protected Phone createPhone(String type) {

        if ("5s".equals(type)) {
            return new Iphone5s();
        } else if ("7".equals(type)) {
            return new Iphone7();
        }

        throw new RuntimeException();
    }
}
