package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator.napoje;

import java.math.BigDecimal;

public class Kawa extends Napoj {
    @Override
    public String getOpis() {
        return "Kawa";
    }

    @Override
    public BigDecimal koszt() {
        return new BigDecimal("2");
    }
}
