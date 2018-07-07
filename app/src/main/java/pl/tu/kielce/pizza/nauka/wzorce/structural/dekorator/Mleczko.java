package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator;

import java.math.BigDecimal;

public class Mleczko extends SkladnikDekorator {

    public Mleczko(Napoj napoj) {
        this.napoj = napoj;
    }

    @Override
    String getOpis() {
        return napoj.getOpis() + " mleczko";
    }

    @Override
    BigDecimal koszt() {
        return napoj.koszt().add(new BigDecimal("0.20"));
    }
}
