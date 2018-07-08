package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.fabryki;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.Phone;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.SonyPhone;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory.telefony.XiaomiPhone;

public class FabrykaAndroidow extends FabrykaTelefonow {
    @Override
    protected Phone createPhone(String type) {

        if ("xiaomi".equals(type)) {
            return new XiaomiPhone();
        } else if ("sony".equals(type)) {
            return new SonyPhone();
        }

        throw new RuntimeException();
    }
}
