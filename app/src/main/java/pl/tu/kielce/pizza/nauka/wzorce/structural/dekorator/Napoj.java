package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator;

import java.math.BigDecimal;

public abstract class Napoj {

    String opis;

    abstract String getOpis();

    abstract BigDecimal koszt();
}
