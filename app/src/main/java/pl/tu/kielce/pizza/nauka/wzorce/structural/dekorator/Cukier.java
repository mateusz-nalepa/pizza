package pl.tu.kielce.pizza.nauka.wzorce.structural.dekorator;

import java.math.BigDecimal;

public class Cukier extends SkladnikDekorator {

    public Cukier(Napoj napoj) {
        this.napoj = napoj;
    }

    @Override
    String getOpis() {
        return napoj.getOpis() + " cukier";
    }

    @Override
    BigDecimal koszt() {
        return napoj.koszt().add(new BigDecimal("0.15"));
    }
}
