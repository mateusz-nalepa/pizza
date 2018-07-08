package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory;

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
