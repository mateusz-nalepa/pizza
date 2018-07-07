package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator;

import java.math.BigDecimal;

public class Kawa extends Napoj {
    @Override
    String getOpis() {
        return "Kawa";
    }

    @Override
    BigDecimal koszt() {
        return new BigDecimal("2");
    }
}
