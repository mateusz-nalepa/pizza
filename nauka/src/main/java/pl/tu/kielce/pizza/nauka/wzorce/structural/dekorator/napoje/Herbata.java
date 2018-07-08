package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.napoje;

import java.math.BigDecimal;

public class Herbata extends Napoj {
    @Override
    public String getOpis() {
        return "Herbata";
    }

    @Override
    public BigDecimal koszt() {
        return new BigDecimal("1");
    }
}
