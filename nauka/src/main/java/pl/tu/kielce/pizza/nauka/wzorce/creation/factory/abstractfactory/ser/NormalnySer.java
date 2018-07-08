package pl.tu.kielce.pizza.nauka.wzorce.creation.factory.abstractfactory.ser;

public class NormalnySer implements Ser {



    @Override
    public void wyswietlNazwe() {
        System.out.println("normalny ser");
    }


    @Override
    public String toString() {
        return "NormalnySer{}";
    }
}
