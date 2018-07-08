package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.methodfactory;

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
