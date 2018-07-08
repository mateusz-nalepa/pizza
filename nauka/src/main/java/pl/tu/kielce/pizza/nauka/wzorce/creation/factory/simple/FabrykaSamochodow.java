package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple.auto.AutoDiesel;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple.auto.AutoNaGaz;
import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple.auto.Car;

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
