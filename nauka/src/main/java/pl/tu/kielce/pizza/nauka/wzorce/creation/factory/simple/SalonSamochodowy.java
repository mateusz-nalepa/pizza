package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple;

import pl.tu.kielce.pizza.nauka.wzorce.creation.factory.simple.auto.Car;

public class SalonSamochodowy {

    public static void main(String[] args) {
        FabrykaSamochodow fabrykaSamochodow = new FabrykaSamochodow();
        Car gaz = fabrykaSamochodow.createCar("gaz");
        System.out.println(gaz);
    }
}
