package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.napoje;

import java.math.BigDecimal;

public abstract class Napoj {

    String opis;

    public abstract String getOpis();

    public abstract BigDecimal koszt();
}
