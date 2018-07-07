package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator;

import java.math.BigDecimal;

public class Herbata extends Napoj {
    @Override
    String getOpis() {
        return "Herbata";
    }

    @Override
    BigDecimal koszt() {
        return new BigDecimal("1");
    }
}
