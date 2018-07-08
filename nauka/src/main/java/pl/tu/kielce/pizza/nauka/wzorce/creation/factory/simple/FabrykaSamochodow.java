package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple;

public class FabrykaSamochodow {


    public Car createCar(String type) {
        if ("gaz".equals(type)) {
            return new AutoNaGaz();
        } else if ("diesel".equals(type)){
            return new AutoDiesel();
        }

        throw new RuntimeException("Nie ma takiego typu samochodu");
    }

}
