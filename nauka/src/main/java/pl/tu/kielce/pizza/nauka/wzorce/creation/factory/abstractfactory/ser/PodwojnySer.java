package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser;

public class PodwojnySer implements Ser {
    @Override
    public void wyswietlNazwe() {
        System.out.println("podwojny ser");
    }

    @Override
    public String toString() {
        return "PodwojnySer{}";
    }

}
